// issue.html
// URL 매개변수 추출

setIssue();

function setIssue() {
    const urlParams = new URLSearchParams(window.location.search);
    let data = JSON.parse(urlParams.get('data'));

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
    return time.substring(0, 4) + "년 " + time.substring(5, 7) + "월 " + time.substring(8, 10) + "일 " + time.substring(11, 13) + "시 " + time.substring(14, 16) + "분";
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
