let listStartIndex = 0

const pagingSize = 100

let lastTemplateName = null

let lastPressedButton = null;

window.onload = () => setup()

var readersCount = 0

function showBooks(books, tableName = "table") {
    const table = document.getElementById(tableName)
    for (let i = table.rows.length - 1; i > 0; i--) {
        table.deleteRow(i)
    }
    if (books.length === 0) {
        alert("This reader has no books")
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
    onAllReadersPressed()
}

function onViewRentedPressed() {
    pressButton("view-btn")
    presentTemplate("search-template", "search-card")
}


function onSearchButtonPressed() {
    let field = document.getElementById("idInput")
    let id = field.value
    if (id == null || id.length > 100) {
        alert("Incorrect input. Id must be less than 20 symbols and not empty.")
        return
    }
    axios
        .get('/api/book/user/' + id)
        .then(response => {
            showBooks(response.data, "table-search")
        })
        .catch(error => {
            let table = document.getElementById("table-search");
            for (let i = table.rows.length - 1; i > 0; i--) {
                table.deleteRow(i)
            }
            alert("There is no user with this card number")
        })
}

function onAddReaderPressed() {
    pressButton("add-btn")
    presentTemplate("add-template", "add-card")
}

function onAddPerformed() {
    let field = document.getElementById("inputName")
    let name = field.value
    if (name == null || name.trim().length === 0 || name.length > 100) {
        alert("Incorrect input. Name must be less than 100 symbols and not empty.")
        return
    }
    field.value = ""
    axios
        .post('/api/reader/add/', {readerName: name.trim()})
        .then(response => {
            showSuccessBar("success-add-label")
        })
        .catch(error => {
            alert("Can't add reader: " + error.response.data.message)
        })
}

async function showSuccessBar(labelName) {
    let label = document.getElementById(labelName)
    label.hidden = false
    setTimeout(() => label.hidden = true, 1000)
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

function onReaderViewPerformed(cardNumber) {
    onViewRentedPressed()
    let cardInput = document.querySelector("#idInput")
    cardInput.value = cardNumber
    onSearchButtonPressed()
}

function showReaders(readers) {
    const table = document.getElementById("tableAll")
    for (let i = 1; i < table.rows.length; i++) {
        table.deleteRow(i)
    }
    for (let i = 0; i < readers.length; i++) {
        let row = table.insertRow(i + 1)
        let reader = readers[i]
        row.insertCell(0).innerText = reader.name
        row.insertCell(1).innerText = reader.cardNumber

        const btn = document.createElement('button');
        btn.className = "btn-search-id";
        btn.innerText = "GO"
        btn.setAttribute("class", "btn btn-secondary")
        btn.onclick = () => {
            onReaderViewPerformed(reader.cardNumber)
        };
        row.insertCell(2).appendChild(btn);
    }
}

function showRangeButtons() {
    let nextButton = document.getElementById("paging-next-btn")
    let prevButton = document.getElementById("paging-prev-btn")
    prevButton.hidden = listStartIndex === 0;
    nextButton.hidden = readersCount <= listStartIndex + pagingSize;
}

function onPagingPrevButtonPressed() {
    listStartIndex -= pagingSize;
    onAllReadersPressed()
}

function onPagingNextButtonPressed() {
    listStartIndex += pagingSize;
    onAllReadersPressed()
}

async function onAllReadersPressed() {
    pressButton("allBtn")
    readersCount = (await axios.get("/api/reader/count")).data
    axios
        .get('/api/reader/all?from=' + listStartIndex + "&limit=" + pagingSize)
        .then(response => {
            presentTemplate("table-template", "table-card")
            showRangeButtons()
            showReaders(response.data)
        });
}