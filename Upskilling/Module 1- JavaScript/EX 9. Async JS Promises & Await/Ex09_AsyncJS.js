function mockFetchEvents() {
    return new Promise(function (resolve, reject) {
        setTimeout(function () {
            resolve([
                { name: "Cultural Fest", date: "July 12", seats: 5 },
                { name: "Health Camp", date: "July 19", seats: 8 },
                { name: "Sports Meet", date: "August 15", seats: 6 },
                { name: "Food Festival", date: "August 22", seats: 3 },
            ]);
        }, 1500);
    });
}

function showSpinner(visible) {
    document.getElementById("spinner").style.display = visible ? "block" : "none";
}

function showError(msg) {
    document.getElementById("errorMsg").textContent = msg;
}

function renderEvents(events) {
    const container = document.getElementById("eventContainer");
    container.innerHTML = "";
    events.forEach(function (ev) {
        const card = document.createElement("div");
        card.className = "event-card";
        card.innerHTML = `<h3>${ev.name}</h3><p>Date: ${ev.date}</p><p>Seats: ${ev.seats}</p>`;
        container.appendChild(card);
    });
}

function loadWithPromise() {
    showSpinner(true);
    showError("");
    document.getElementById("eventContainer").innerHTML = "";
    mockFetchEvents()
        .then(function (data) { showSpinner(false); renderEvents(data); })
        .catch(function (err) { showSpinner(false); showError(err.message); });
}

async function loadWithAsync() {
    showSpinner(true);
    showError("");
    document.getElementById("eventContainer").innerHTML = "";
    try {
        const data = await mockFetchEvents();
        showSpinner(false);
        renderEvents(data);
    } catch (err) {
        showSpinner(false);
        showError(err.message);
    }
}
