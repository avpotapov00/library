const listStartIndex = 0;

let lastTemplateName = null

const letters = /^[A-Za-zА-Яа-я]+$/;

let lastPressedButton = null;

window.onload = () => setup()


function showBooks(books, tableName = "table") {
    const table = document.getElementById(tableName)
    for (let i = 1; i < table.rows.length; i++) {
        table.deleteRow(i)
    }
    for (let i = 0; i < books.length; i++) {
        let row = table.insertRow(i + 1)
        let book = books[i]
        row.insertCell(0).innerText = i + 1
        row.insertCell(1).innerText = book.name
        row.insertCell(2).innerText = book.author
        row.insertCell(3).innerText = book.code
    }

}


function presentTemplate(templateName, name) {
    let content = document.getElementById("content")
    if (lastTemplateName != null) {
        document.getElementById(lastTemplateName).remove()
    }
    let template = document.querySelector("#" + templateName)
    let clone = document.importNode(template.content, true)
    content.appendChild(clone)
    lastTemplateName = name
}

async function setup() {
    onAllBooksPressed()
}

function pressButton(buttonId) {
    if (lastPressedButton != null) {
        let lastClassName = lastPressedButton.className
        lastPressedButton.setAttribute("class", lastClassName.replace("primary", "secondary"));
    }
    const button = document.getElementById(buttonId)
    let nextClassName = button.className
    button.setAttribute("class", nextClassName.replace("secondary", "primary"))
    lastPressedButton = button
}

function onAllBooksPressed() {
    pressButton("allBtn")
    axios
        .get('/api/book/all?from=' + listStartIndex + "&limit=500")
        .then(response => {
            presentTemplate("table-template", "table")
            showBooks(response.data)
        });

}

function onInfoPressed() {
    pressButton("infoBtn")
    presentTemplate("search-template", "searchForm")
}

function onSearchButtonPressed() {
    let field = document.getElementById("codeInput")
    let code = field.value
    if (code == null || code.length > 100) {
        alert("Incorrect input. Code must be less than 100 symbols and not empty.")
        return
    }
    axios
        .get('/api/book/' + code)
        .then(response => {
            console.log(response)
            showBooks([response.data], "table-search")
        })
        .catch(error => {
            alert("There is no book with this code")
        })
}


function onAddPressed() {
    pressButton("addBtn")
    presentTemplate("add-template", "add-card")
}

function onAddPerformed() {
    let nameInput = document.getElementById("inputName")
    let codeInput = document.getElementById("inputCode")
    let authorInput = document.getElementById("inputAuthor")

    let name = nameInput.value
    let code = codeInput.value
    let author = authorInput.value


    nameInput.value = ""
    codeInput.value = ""
    authorInput.value = ""

    if (name == null || name.trim().length === 0 || !name[0].match(letters) || name.length > 255) {
        alert("Name must be not empty and less than 255 symbols")
        return
    }

    if (code == null || code.trim().length === 0 || code.length > 255) {
        alert("Code must be not empty and less than 255 symbols")
        return
    }

    if (author == null || !author.match(letters) || author.trim().length === 0 || author.length > 255) {
        alert("Author must be literal string, not empty and less than 255 symbols")
        return
    }

    axios.put('/api/book/add', {author: author.trim(), name: name.trim(), code: code.trim()})
        .then(response => {
            showSuccessBar("success-add-label")
        })
        .catch(error => {
            alert("Can't add book: " + error.response.data.message)
        })
}


async function showSuccessBar(labelName) {
    let label = document.getElementById(labelName)
    label.hidden = false
    setTimeout(() => label.hidden = true, 1000)
}


function onUpdatePressed() {
    pressButton("updBtn")
    presentTemplate("update-template", "update-card")
}


function onUpdatePerformed() {
    let oldCodeInput = document.getElementById("old-code")
    let newCodeInput = document.getElementById("new-code")

    let oldCode = oldCodeInput.value
    let newCode = newCodeInput.value

    oldCodeInput.value = ""
    newCodeInput.value = ""

    if (oldCode == null || oldCode.trim().length === 0 || oldCode.length > 255) {
        alert("Old code must be not empty and less than 255 symbols")
        return
    }

    if (newCode == null || newCode.trim().length === 0 || newCode.length > 255) {
        alert("New code must be not empty and less than 255 symbols")
        return
    }

    console.log(oldCode, newCode)

    axios.post('/api/book/change', {oldCode: oldCode.trim(), newCode: newCode.trim()})
        .then(response => {
            showSuccessBar("success-update-label")
        })
        .catch(error => {
            alert("Can't edit code: " + error.response.data.message)
        })
}

function onDeletePressed() {
    pressButton("deleteBtn")
    presentTemplate("delete-template", "delete-card")
}

function onDeletePerformed() {
    let codeInput = document.getElementById("delete-code")
    let code = codeInput.value
    codeInput.value = ""

    if (code == null || code.trim().length === 0 || code.length > 255) {
        alert("Code must be not empty and less than 255 symbols")
        return
    }

    axios.delete('/api/book/' + code)
        .then(response => {
            showSuccessBar("success-delete-label")
        })
        .catch(error => {
            alert("Can't delete book: " + error.response.data.message)
        })

}