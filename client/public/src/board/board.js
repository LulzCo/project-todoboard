userId = "성";
callBoard(userId);

function callBoard(userId) {
    // POST 요청 보내기
    const url = 'http://localhost:8080/issue/read/' + userId;

    fetch(url, {
      method: 'GET',
      headers: {
        'Content-Type': 'application/json'
      },
    })
    .then(response => {
        if (response.ok) {
            return response.json();
        } else {
            throw new Error('오류 발생');
        }
    })
    .then(data => {
        updateBoard(data);
    })
    .catch(error => {
      // 오류 처리
      console.error('Error:', error);
    });
}

function updateBoard(data) {
    for (let i = 0; i < data.length; i++) {
        if (data[i].status == "TODO") {
            setBoard(data, "TODO", i);
        } else if (data[i].status == "DOING") {
            setBoard(data, "DOING", i);
        } else if (data[i].status == "DONE") {
            setBoard(data, "DONE", i);
        } else if (data[i].status == "BACKLOG") {
            setBoard(data, "BACKLOG", i);
        }
    }

    // 드래그 기능을 위해 새로 세팅
    let = issues = document.querySelectorAll('.issue');
    issues.forEach(issue => {
        issue.addEventListener('dragstart', dragStart);
        issue.addEventListener('dragend', dragEnd);
    });
}

function setBoard(data, status, i) {
    let parentElement = document.querySelector('#' + status);
    let newIssue = document.createElement('div');
    newIssue.className = 'issue';
    newIssue.draggable = true;
    newIssue.textContent = data[i].title;
    newIssue.addEventListener('click', function() {
        openModal(data[i]);
    });
    parentElement.appendChild(newIssue);
}



let draggingIssue = null;

function dragStart() {
    draggingIssue = this;
    this.classList.add('dragging');
}

function dragEnd() {
    this.classList.remove('dragging');
    draggingIssue = null;
}

const columns = document.querySelectorAll('.column');

columns.forEach(column => {
    column.addEventListener('dragover', dragOver);
    column.addEventListener('dragenter', dragEnter);
    column.addEventListener('dragleave', dragLeave);
    column.addEventListener('drop', drop);
});

function dragOver(e) {
    e.preventDefault();
}

function dragEnter(e) {
    e.preventDefault();
    this.classList.add('hovered');
}

function dragLeave() {
    this.classList.remove('hovered');
}

function drop() {
    this.appendChild(draggingIssue);
    this.classList.remove('hovered');
}

const modalOverlay = document.getElementById('modalOverlay');
const modalContainer = document.getElementById('modalContainer');

function openModal(issue) {
    modalOverlay.classList.add('active');

    const editButton = document.querySelector('.edit-button');
    const saveButton = document.querySelector('.save-button');
    const deleteButton = document.querySelector('.delete-button');
    if (issue) {
        setIssue(issue);
        editButton.style.display = 'inline';
        saveButton.style.display = 'inline';
        deleteButton.style.display = 'inline';
    } else {
        editButton.style.display = 'none';
        saveButton.style.display = 'inline';
        deleteButton.style.display = 'none';
    }
}

function closeModal() {
    modalOverlay.classList.remove('active');
    document.getElementById("title").value = '';
    document.getElementById("tag").value = '';
    document.getElementById("status").value = '';
    document.getElementById("contents").value = '';
    document.getElementById("createdAt").innerText = '';
    document.getElementById("updatedAt").innerText = '';
    document.getElementById("dueType").value = '';
    document.getElementById("dueDate").value = '';
}



/////////////////////////////////////////////////////////////////////////////////issue
function setIssue(data) {
    document.getElementById("title").value = data.title;
    document.getElementById("tag").value = data.tagName;
    document.getElementById("status").value = data.status;
    document.getElementById("contents").value = data.contents;
    document.getElementById("createdAt").innerText = timeToString(data.createdAt);
    document.getElementById("updatedAt").innerText = timeToString(data.updatedAt);
    document.getElementById("dueType").value = data.dueType;
    document.getElementById("dueDate").value = timeToString(data.dueDate);
}

function timeToString(time) {
    return time.substring(0, 4) + "년" + time.substring(5, 7) + "월" + time.substring(8, 10) + "일 - " + time.substring(11, 13) + "시" + time.substring(14, 16) + "분";
}

function editIssue() {
  const issueFields = document.getElementsByClassName('field-value');
  const editButton = document.querySelector('.edit-button');
  const saveButton = document.querySelector('.save-button');

  // Enable editing for input fields and textarea
  Array.from(issueFields).forEach((field) => {
    field.disabled = false;
  });

  // Toggle visibility of buttons
  editButton.style.display = 'none';
  saveButton.style.display = 'block';
}

function saveIssue() {
  const issueFields = document.getElementsByClassName('field-value');
  const editButton = document.querySelector('.edit-button');
  const saveButton = document.querySelector('.save-button');

  // Disable editing for input fields and textarea
  Array.from(issueFields).forEach((field) => {
    field.disabled = true;
  });

  // Toggle visibility of buttons
  editButton.style.display = 'block';
  saveButton.style.display = 'none';
}

function deleteIssue() {
  const issueFields = document.getElementsByClassName('field-value');
  const editButton = document.querySelector('.edit-button');
  const saveButton = document.querySelector('.save-button');

  // Disable editing for input fields and textarea
  Array.from(issueFields).forEach((field) => {
    field.disabled = true;
  });

  // Toggle visibility of buttons
  editButton.style.display = 'block';
  saveButton.style.display = 'none';
}
