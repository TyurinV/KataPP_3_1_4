const usersList = document.querySelector('.table table-striped, tbody');
const userInfo = document.getElementById('currentUser');
const addUser = document.querySelector('#profileNEW');
//сюда выводим инфу о юзере
const header = document.getElementById('headTOP');
//модалка edit и delete
const modalForm = new bootstrap.Modal(document.getElementById('editModal'))
const formEdit = document.getElementById('editForm');
const modalFormDel = new bootstrap.Modal(document.getElementById('deleteModal'))
const formDelete = document.getElementById('deleteModal');


// для создания нового пользователя в JSON
const firstNameValue = document.getElementById('firstName')
const passwordValue = document.getElementById('password')
const lastNameValue = document.getElementById('lastName')
const sexValue = document.getElementById('sex')
const smokerValue = document.getElementById('smoker')
const rolesValue = document.getElementById('admin')
// для edit
const idEdit = document.getElementById('idEdit')
const nameEdit = document.getElementById('firstNameEdit')
const lastNameEdit = document.getElementById('lastNameEdit')
const passwordEdit = document.getElementById('passwordEdit')
const sexEdit = document.getElementById('sexEdit')
var smokeEdit = document.getElementById('smokerEdit')
var adminEdit = document.getElementById('adminEdit')
//для delete
const idDelete = document.getElementById('idDelete')
const firstNameDelete = document.getElementById('firstNameDelete')
const lastNameDelete = document.getElementById('lastNameDelete')
const sexDelete = document.getElementById('sexDelete')
var smokerDelete = document.getElementById('smokerDelete')
var adminDelete = document.getElementById('adminDelete')
//для roles


let output = '';

const renderNewUserTableBody = (users) => {
    users.forEach(user => {
        output += ` 
                                   <tr>
                                    <td>${user.id}</td> 
                                    <td>${user.firstName}</td>
                                    <td>${user.lastName}</td>
                                    <td>${user.sex}</td>
                                    <td>${user.smoker}</td>
                                    <td>${user.roles.map(r => r.roleName.replace('ROLE_', '')).join(', ')}</td>

                                    <td>
                                        <!--                                Кнопка модалки ЕДИТ-->
                                        <a class="btn btn-primary btne"
                                         id="edit-user">Edit </a>
                                            </td>
                                    <!--                                Кнопка delete-->
                                    <td><a class="btn btn-danger btnd"
                                           id="delete-user">Delete</a>
                                    </td>
                                `;
    });
    usersList.innerHTML = output;
}

const url = 'http://localhost:8080/api/users';

//get all users rows
fetch(url)
    .then(res => res.json())
    .then(data => renderNewUserTableBody(data))


// New User Post
addUser.addEventListener('submit', (e) => {
    e.preventDefault();
    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            firstName: firstNameValue.value,
            password: passwordValue.value,
            lastName: lastNameValue.value,
            sex: sexValue.value,
            smoker: smokerValue.checked,
            roles: rolesValue.checked ? [{"id": 1, "roleName": "ROLE_ADMIN"}, {
                "id": 2,
                "roleName": "ROLE_USER"
            }] : [{"id": 2, "roleName": "ROLE_USER"}]
        })
    })
        .then(res => res.json())
        .then(data => {
            const dataArr = [];
            dataArr.push(data);
            renderNewUserTableBody(dataArr);
        })
})


//Текст о юзере с ролями в шапку
fetch('http://localhost:8080/api/userAuth/')
    .then((response) => {
        return response.text();
    })
    .then((data) => {
        header.innerText = data;
    });

//edit user
//вспомогательный
const on = (element, event, selector, handler) => {
    element.addEventListener(event, e => {
        if (e.target.closest(selector)) {
            handler(e)
        }
    })
}

let idForm = 0;
on(document, 'click', '.btne', e => {

        const fill = e.target.parentNode.parentNode
        idForm = fill.children[0].innerHTML
        const name = fill.children[1].innerHTML
        const lastName = fill.children[2].innerHTML
        const password = ''
        const sex = fill.children[3].innerHTML
        let smoker = fill.children[4].innerHTML
        let admin = fill.children[5].innerHTML

        console.log(smoker)
        console.log(admin)

        idEdit.value = idForm
        nameEdit.value = name
        lastNameEdit.value = lastName
        passwordEdit.value = ''
        sexEdit.value = sex
        smokeEdit.checked = (smoker == 'true');
        adminEdit.checked = (admin.includes('ADMIN'));

        modalForm.show()

    }
)
formEdit.addEventListener('submit', (e) => {
    e.preventDefault()

    fetch(url, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            id: idForm,
            firstName: nameEdit.value,
            password: passwordEdit.value,
            lastName: lastNameEdit.value,
            sex: sexEdit.value,
            smoker: smokeEdit.checked,
            roles: adminEdit.checked ? [{"id": 1, "roleName": "ROLE_ADMIN"}, {
                "id": 2,
                "roleName": "ROLE_USER"
            }] : [{"id": 2, "roleName": "ROLE_USER"}]
        })
    })
        .then(res => res.json())
        .then(res => location.reload())

    modalForm.hide()
})


//delete
let idDel = 0;
on(document, 'click', '.btnd', e => {

        const fill = e.target.parentNode.parentNode
        idDel = fill.children[0].innerHTML
        const name = fill.children[1].innerHTML
        const lastName = fill.children[2].innerHTML
        const sex = fill.children[3].innerHTML
        let smoker = fill.children[4].innerHTML
        let admin = fill.children[5].innerHTML

        idDelete.value = idDel
        firstNameDelete.value = name
        lastNameDelete.value = lastName
        sexDelete.value = sex
        smokerDelete.checked = (smoker == 'true')
        adminDelete.checked = (admin.includes('ADMIN'));

        modalFormDel.show()

    }
)
formDelete.addEventListener('submit', (e) => {
    e.preventDefault()
    fetch(`${url}/${idDel}`, {
        method: 'DELETE'
    })
        // .then(res => res.json())
        .then(() => location.reload())

    modalFormDel.hide()
})


// Вызываем текущего пользователя
let outputUserInfo = '';
fetch('http://localhost:8080/api/currentUser')
    .then((res) => res.json())
    .then((user) => {
        outputUserInfo += `<tr>
            <td>${user.id}</td>
            <td>${user.username}</td>
            <td>${user.lastName}</td>
            <td>${user.sex}</td>
            <td>${user.smoker}</td>       
            <td>${user.roles.map(r => r.roleName.replace('ROLE_', '')).join(', ')}</td>       
            </tr>`;
        userInfo.innerHTML = outputUserInfo;
    });



