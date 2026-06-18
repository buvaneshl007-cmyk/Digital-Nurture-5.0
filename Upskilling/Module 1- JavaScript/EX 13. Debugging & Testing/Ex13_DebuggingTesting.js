const logs = [];

function addLog(msg) {
    logs.push("[" + new Date().toLocaleTimeString() + "] " + msg);
    document.getElementById("logOutput").innerHTML = logs.join("<br>");
    console.log(msg);
}

function submitWithDebug() {
    logs.length = 0;
    const statusMsg = document.getElementById("statusMsg");
    statusMsg.textContent = "";

    addLog("Step 1: Submit button clicked.");

    const name = document.getElementById("name").value.trim();
    const email = document.getElementById("email").value.trim();
    const event = document.getElementById("event").value;

    addLog("Step 2: Captured — name: '" + name + "', email: '" + email + "', event: '" + event + "'");

    debugger;

    if (!name) { addLog("ERROR: Name is empty."); statusMsg.className = "error"; statusMsg.textContent = "Validation failed: Name is required."; return; }
    if (!email || !/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) { addLog("ERROR: Invalid email."); statusMsg.className = "error"; statusMsg.textContent = "Validation failed: Enter a valid email."; return; }
    if (!event) { addLog("ERROR: No event selected."); statusMsg.className = "error"; statusMsg.textContent = "Validation failed: Please select an event."; return; }

    addLog("Step 3: Validation passed.");

    const payload = { name, email, event };
    addLog("Step 4: Payload — " + JSON.stringify(payload));
    addLog("Step 5: Sending fetch POST...");

    fetch("https://jsonplaceholder.typicode.com/posts", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify(payload)
    })
    .then(function (res) { addLog("Step 6: Response status " + res.status); return res.json(); })
    .then(function (data) {
        addLog("Step 7: Success — mock ID: " + data.id);
        statusMsg.className = "success";
        statusMsg.textContent = "Submitted! Check Network tab in DevTools for the request payload.";
    })
    .catch(function (err) { addLog("ERROR: " + err.message); statusMsg.className = "error"; statusMsg.textContent = "Request failed: " + err.message; });
}
