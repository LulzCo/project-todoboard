const userId = "원";
let tagId = null;
callBoard(userId);
callTag(userId);
function callBoard(userId) {
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

function callTag(userId) {
    const url = 'http://localhost:8080/tag/read/' + userId

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
        setTags(data);
    })
    .catch(error => {
      // 오류 처리
      console.error('Error:', error);
    });
}

function setTags(data) {
    let selectElement = document.querySelector('.tags');
    let selectElement2 = document.querySelector('#tag');
    for (let i = 0; i < data.length; i++) {
        let newTag = document.createElement('option');
        newTag.value = data[i].id;
        newTag.id = 'tag' + data[i].id;
        newTag.textContent = data[i].tagName;
        selectElement.appendChild(newTag);
        let copiedTag = newTag.cloneNode(true);
        selectElement2.appendChild(copiedTag);
    }
}

function selectedTag() {
    tagId = document.querySelector('.tags').value;
    if (tagId == "전체") {
        callBoard(userId);
    } else {
        const url = 'http://localhost:8080/issue/read/' + userId + '/' + tagId

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
}


function clear() {
    let issues = document.querySelectorAll('.issue');
    for(let i = 0; i < issues.length; i++) {
        let parentElement = issues[i].parentNode;
        parentElement.removeChild(issues[i]);
    }
}

function updateBoard(data) {
    clear();
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
    // 드래그 세팅
    newIssue.addEventListener('dragstart', dragStart);
    newIssue.addEventListener('dragend', function() {
        dragEnd.call(this, data[i]);
    });
    parentElement.appendChild(newIssue);
}



let draggingIssue = null;

function dragStart() {
    draggingIssue = this;
    this.classList.add('dragging');
}

function dragEnd(data) {
    data.status = this.parentElement.id;
//    data.status =
    // POST 요청 보내기
        const url = 'http://localhost:8080/issue/update';

        fetch(url, {
          method: 'POST',
          headers: {
            'Content-Type': 'application/json'
          },
          body: JSON.stringify(data)
        })
        .then(response => response.text())
        .then(result => {
            this.classList.remove('dragging');
        })
        .catch(error => {
          // 오류 처리
          console.error('Error:', error);
        });
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
    const updateButton = document.querySelector('.update-button');
    const issueFields = document.getElementsByClassName('field-value');
    if (issue) {
        setIssue(issue);
        editButton.style.display = 'inline';
        saveButton.style.display = 'none';
        deleteButton.style.display = 'none';
        updateButton.style.display = 'none';

        // Disable editing for input fields and textarea
        Array.from(issueFields).forEach((field) => {
          field.disabled = true;
        });
    } else {
        editButton.style.display = 'none';
        saveButton.style.display = 'inline';
        deleteButton.style.display = 'none';
        updateButton.style.display = 'none';

        let selectedOption = tagId;
        // select 요소 가져오기
        let selectElement = document.getElementById('tag');
        // select 요소의 옵션들을 순회하며 선택 상태 설정
        for (let i = 0; i < selectElement.options.length; i++) {
          let option = selectElement.options[i];
          if (option.value == selectedOption) {
            option.selected = true;
            break;  // 선택되었으므로 더 이상 순회할 필요 없음
          }
        }

        Array.from(issueFields).forEach((field) => {
            field.disabled = false;
        });
    }
}

function closeModal() {
    modalOverlay.classList.remove('active');
    document.getElementById("title").value = '';
    document.getElementById("tag").value = '';
    document.getElementById("status").value = '';
    document.getElementById("contents").value = '';
//    document.getElementById("createdAt").innerText = '';
//    document.getElementById("updatedAt").innerText = '';
    document.getElementById("dueType").value = '';
    document.getElementById("dueDate").value = '';

    selectedTag();
}



/////////////////////////////////////////////////////////////////////////////////issue
function setIssue(data) {
    document.getElementById("issueId").value = data.id;
    document.getElementById("title").value = data.title;

//    document.getElementById('tag').selected = 'tag' + data.tag.id;
    // 선택할 옵션 값
    var selectedOption = data.tag.id;
    // select 요소 가져오기
    var selectElement = document.getElementById('tag');
    // select 요소의 옵션들을 순회하며 선택 상태 설정
    for (let i = 0; i < selectElement.options.length; i++) {
      let option = selectElement.options[i];
      if (option.value == selectedOption) {
        option.selected = true;
        break;  // 선택되었으므로 더 이상 순회할 필요 없음
      }
    }

    document.getElementById("status").value = data.status;
    document.getElementById("contents").value = data.contents;
//    document.getElementById("createdAt").innerText = timeToString(data.createdAt);
//    document.getElementById("updatedAt").innerText = timeToString(data.updatedAt);
    document.getElementById("dueType").value = data.dueType;
    document.getElementById("dueDate").value = timeToString(data.dueDate);
}

function timeToString(time) {
    return time;
//    return time.substring(0, 4) + "년" + time.substring(5, 7) + "월" + time.substring(8, 10) + "일 - " + time.substring(11, 13) + "시" + time.substring(14, 16) + "분";
}

function editIssue() {
  const issueFields = document.getElementsByClassName('field-value');
  const editButton = document.querySelector('.edit-button');
  const saveButton = document.querySelector('.save-button');
  const deleteButton = document.querySelector('.delete-button');
  const updateButton = document.querySelector('.update-button');

  // Enable editing for input fields and textarea
  Array.from(issueFields).forEach((field) => {
    field.disabled = false;
  });

  // Toggle visibility of buttons
  editButton.style.display = 'none';
  deleteButton.style.display = 'inline';
  saveButton.style.display = 'none';
  updateButton.style.display = 'inline';
}

function updateIssue() {
    const issueFields = document.getElementsByClassName('field-value');
    const editButton = document.querySelector('.edit-button');
    const saveButton = document.querySelector('.save-button');
    const deleteButton = document.querySelector('.delete-button');

    // Disable editing for input fields and textarea
    Array.from(issueFields).forEach((field) => {
        field.disabled = true;
    });

    // Toggle visibility of buttons
    editButton.style.display = 'inline';
    deleteButton.style.display = 'none';
    saveButton.style.display = 'none';
}

function saveIssue() {
    const issueFields = document.getElementsByClassName('field-value');
    const editButton = document.querySelector('.edit-button');
    const saveButton = document.querySelector('.save-button');
    const deleteButton = document.querySelector('.delete-button');
    const updateButton = document.querySelector('.update-button');

    // Disable editing for input fields and textarea
    Array.from(issueFields).forEach((field) => {
        field.disabled = true;
    });

    // Toggle visibility of buttons
    editButton.style.display = 'inline';
    deleteButton.style.display = 'none';
    saveButton.style.display = 'none';
    updateButton.style.display = 'none';

    // POST 요청 보내기
    const url = 'http://localhost:8080/issue/create';

    data = {
        userId: userId,
        title: document.getElementById("title").value,
        tagId: document.getElementById("tag").value,
        status: "TODO",
        contents: document.getElementById("contents").value,
        dueType: "DEADLINE",
        dueDate: null,
    }

    fetch(url, {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(data)
    })
    .then(response => response.text())
    .then(result => {
//        alert(result);
    })
    .catch(error => {
      console.error('Error:', error);
    });
}

function deleteIssue() {
        const id = document.getElementById("issueId").value;
        const url = 'http://localhost:8080/issue/delete/' + id;
        fetch(url, {
          method: 'DELETE',
        })
        .then(response => response.text())
        .then(result => {
            alert(result);
            closeModal();
        })
        .catch(error => {
          console.error('Error:', error);
        });
}
