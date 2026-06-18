function Event(name, date, category, seats) {
    this.name = name;
    this.date = date;
    this.category = category;
    this.seats = seats;
}

Event.prototype.checkAvailability = function () {
    return this.seats > 0;
};

const events = [
    new Event("Cultural Fest", "July 12, 2024", "Cultural", 5),
    new Event("Health Camp", "July 19, 2024", "Health", 0),
    new Event("Sports Meet", "August 15, 2024", "Sports", 8),
];

const container = document.getElementById("eventList");

events.forEach(function (ev) {
    const available = ev.checkAvailability();
    const entries = Object.entries(ev).map(([k, v]) => `${k}: ${v}`).join(" &nbsp;|&nbsp; ");
    const card = document.createElement("div");
    card.className = "event-card";
    card.innerHTML = `
        <h3>${ev.name}</h3>
        <p>Date: ${ev.date} &nbsp;|&nbsp; Category: ${ev.category}</p>
        <p>Seats: ${ev.seats} &nbsp;|&nbsp;
           Availability: <span class="avail ${available ? 'yes' : 'no'}">${available ? 'Available' : 'Full'}</span>
        </p>
        <div class="entries">Object.entries → ${entries}</div>
    `;
    container.appendChild(card);
});
