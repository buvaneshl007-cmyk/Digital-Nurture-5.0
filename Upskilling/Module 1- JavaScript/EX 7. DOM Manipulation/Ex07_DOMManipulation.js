const events = [
    { id: 1, name: "Cultural Fest", date: "July 12", seats: 3 },
    { id: 2, name: "Health Camp", date: "July 19", seats: 5 },
    { id: 3, name: "Sports Meet", date: "August 15", seats: 2 },
    { id: 4, name: "Food Festival", date: "August 22", seats: 0 },
];

const container = document.querySelector("#eventContainer");

function buildCard(ev) {
    const card = document.createElement("div");
    card.className = "event-card";
    card.id = "card-" + ev.id;

    const title = document.createElement("h3");
    title.textContent = ev.name;

    const date = document.createElement("p");
    date.textContent = "Date: " + ev.date;

    const seatInfo = document.createElement("p");
    seatInfo.className = ev.seats === 0 ? "seats full-tag" : "seats";
    seatInfo.id = "seats-" + ev.id;
    seatInfo.textContent = ev.seats > 0 ? "Seats: " + ev.seats : "Seats: Full";

    const regBtn = document.createElement("button");
    regBtn.className = "reg-btn";
    regBtn.textContent = "Register";
    regBtn.onclick = function () { updateSeats(ev.id, -1); };

    const cancelBtn = document.createElement("button");
    cancelBtn.className = "cancel-btn";
    cancelBtn.textContent = "Cancel";
    cancelBtn.onclick = function () { updateSeats(ev.id, 1); };

    card.appendChild(title);
    card.appendChild(date);
    card.appendChild(seatInfo);
    card.appendChild(regBtn);
    card.appendChild(cancelBtn);
    return card;
}

function updateSeats(id, delta) {
    const ev = events.find(e => e.id === id);
    if (!ev) return;
    ev.seats = Math.max(0, ev.seats + delta);
    const seatEl = document.getElementById("seats-" + id);
    seatEl.textContent = ev.seats > 0 ? "Seats: " + ev.seats : "Seats: Full";
    seatEl.className = ev.seats === 0 ? "seats full-tag" : "seats";
}

events.forEach(function (ev) {
    container.appendChild(buildCard(ev));
});
