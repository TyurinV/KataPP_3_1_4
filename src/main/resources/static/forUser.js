// Вызываем текущего пользователя
const userInfo = document.getElementById('currentUser');
let outputUserInfo = '';

fetch('http://localhost:8080/user/api/currentUser')
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

//сюда выводим инфу о юзере
const header = document.getElementById('headTOP');

//Текст в шапку
fetch('http://localhost:8080/user/api/userAuth/')
    .then((response) => {
        return response.text();
    })
    .then((data) => {
        header.innerText = data;
    });