
const openModalBtn = document.getElementById('openModalBtn');
const closeModalBtn = document.getElementById('closeModalBtn');
const closeModalFooterBtn = document.getElementById('closeModalFooterBtn');
const modal = document.getElementById('reg-modal');

// Function to show the modal
function showModal() {
    modal.style.display = 'block';
    modal.classList.add('show');
    document.body.style.overflow = 'hidden'; // Prevent scrolling when modal is open
}

// Function to hide the modal
function hideModal() {
    modal.style.display = 'none';
    modal.classList.remove('show');
    document.body.style.overflow = ''; // Restore scrolling when modal is closed
}

// Event listeners
openModalBtn.addEventListener('click', showModal);
closeModalBtn.addEventListener('click', hideModal);
closeModalFooterBtn.addEventListener('click', hideModal);

// Optional: Close the modal when clicking outside of it
window.onclick = function(event) {
    if (event.target === modal) {
        hideModal();
    }
}