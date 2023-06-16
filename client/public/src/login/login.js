function handleFormSubmit(event) {
    event.preventDefault(); // 기본 폼 제출 동작 방지

    // 입력값 가져오기
    const userId = document.getElementById('userId').value;
    const password = document.getElementById('password').value;

    // POST 요청 보내기
    const url = 'http://localhost:8080/auth/login';
    const data = {
        userId: userId,
        password: password
    };

    fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
    body: JSON.stringify(data)
    })
    .then(response => response.text())
    .then(result => {
      // 요청에 대한 처리
        if (result == "로그인 완료") {
            localStorage.setItem('userId', data.userId);
            window.location.href = '/board.html';
        } else {
            alert("로그인 실패")
        }
    })
    .catch(error => {
        // 오류 처리
        console.error('Error:', error);
    });
}
// 폼 제출 이벤트 리스너 등록
const form = document.getElementById('login-form');
form.addEventListener('submit', handleFormSubmit);