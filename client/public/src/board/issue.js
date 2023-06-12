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
