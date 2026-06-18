let events = [];

function addEvent(name, date, category, seats) {
    events.push({ name, date, category, seats });
}

function registerUser(eventName) {
    const event = events.find(e => e.name === eventName);
    if (event && event.seats > 0) {
        event.seats--;
        trackRegistration(event.category);
        renderEvents(events);
    }
}

function filterEventsByCategory(list, matchFn) {
    return list.filter(matchFn);
}

function makeRegistrationTracker() {
    const counts = {};
    return function (category) {
        counts[category] = (counts[category] || 0) + 1;
        const display = Object.entries(counts).map(([k, v]) => `${k}: ${v}`).join(" | ");
        document.getElementById("regCount").textContent = "Registrations by category — " + display;
    };
}

const trackRegistration = makeRegistrationTracker();

addEvent("Cultural Fest", "July 12", "cultural", 5);
addEvent("Health Camp", "July 19", "health", 8);
addEvent("Sports Meet", "August 15", "sports", 6);
addEvent("Food Festival", "August 22", "food", 4);
addEvent("Art Exhibition", "September 5", "cultural", 3);

function renderEvents(list) {
    const container = document.getElementById("eventList");
    container.innerHTML = "";
    list.forEach(function (ev) {
        const card = document.createElement("div");
        card.className = "event-card";
        card.innerHTML = `
            <h3>${ev.name} <span class="badge">${ev.category}</span></h3>
            <p>Date: ${ev.date} &nbsp;|&nbsp; Seats: <strong>${ev.seats}</strong></p>
            <button style="margin-top:8px;padding:6px 16px;background:#0d3b6e;color:white;border:none;border-radius:4px;cursor:pointer;font-size:13px;"
                onclick="registerUser('${ev.name}')">Register</button>
        `;
        container.appendChild(card);
    });
}

function applyFilter() {
    const selected = document.getElementById("categoryFilter").value;
    const filtered = selected === "all"
        ? events
        : filterEventsByCategory(events, e => e.category === selected);
    renderEvents(filtered);
}

renderEvents(events);
