const issues = document.querySelectorAll('.issue');

let draggingIssue = null;

issues.forEach(issue => {
    issue.addEventListener('dragstart', dragStart);
    issue.addEventListener('dragend', dragEnd);
});

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

function openModal(task) {
    // AJAX 요청을 통해 issue.html 파일을 가져옴
    const xhr = new XMLHttpRequest();
    xhr.open('GET', 'issue.html', true);
    xhr.onreadystatechange = function() {
      if (xhr.readyState === 4 && xhr.status === 200) {
        modalContainer.innerHTML = xhr.responseText;
        modalOverlay.classList.add('active');
      }
    };
    xhr.send();
}

function closeModal() {
    modalOverlay.classList.remove('active');
    modalContainer.innerHTML = '';
}