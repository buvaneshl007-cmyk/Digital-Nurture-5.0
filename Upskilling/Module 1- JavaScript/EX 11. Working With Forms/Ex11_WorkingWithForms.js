const form = document.getElementById("regForm");

form.addEventListener("submit", function (e) {
    e.preventDefault();

    const name = form.elements["name"].value.trim();
    const email = form.elements["email"].value.trim();
    const event = form.elements["event"].value;

    let valid = true;

    const nameError = document.getElementById("nameError");
    const emailError = document.getElementById("emailError");
    const eventError = document.getElementById("eventError");

    nameError.style.display = "none";
    emailError.style.display = "none";
    eventError.style.display = "none";

    if (!name) { nameError.style.display = "block"; valid = false; }
    if (!email || !/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) { emailError.style.display = "block"; valid = false; }
    if (!event) { eventError.style.display = "block"; valid = false; }

    if (!valid) return;

    const successMsg = document.getElementById("successMsg");
    successMsg.style.display = "block";
    successMsg.textContent = `Registration confirmed! ${name}, you've signed up for "${event}". A confirmation will be sent to ${email}.`;
    form.reset();
});
