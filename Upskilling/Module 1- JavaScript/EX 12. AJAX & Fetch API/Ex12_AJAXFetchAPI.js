function submitRegistration() {
    const name = document.getElementById("name").value.trim();
    const email = document.getElementById("email").value.trim();
    const event = document.getElementById("event").value;
    const spinner = document.getElementById("spinner");
    const statusMsg = document.getElementById("statusMsg");

    if (!name || !email || !event) {
        statusMsg.className = "failure";
        statusMsg.style.display = "block";
        statusMsg.textContent = "Please fill in all fields before submitting.";
        return;
    }

    statusMsg.style.display = "none";
    spinner.style.display = "block";

    const payload = { name, email, event };
    console.log("Submitting payload:", payload);

    setTimeout(function () {
        fetch("https://jsonplaceholder.typicode.com/posts", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(payload)
        })
        .then(function (response) {
            if (!response.ok) throw new Error("Server responded with status " + response.status);
            return response.json();
        })
        .then(function (data) {
            spinner.style.display = "none";
            statusMsg.className = "success";
            statusMsg.style.display = "block";
            statusMsg.textContent = `Registration successful! ${name} has been registered for "${event}". (Mock ID: ${data.id})`;
            console.log("Server response:", data);
        })
        .catch(function (err) {
            spinner.style.display = "none";
            statusMsg.className = "failure";
            statusMsg.style.display = "block";
            statusMsg.textContent = "Submission failed: " + err.message;
        });
    }, 1500);
}
