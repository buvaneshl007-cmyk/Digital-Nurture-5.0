const events = [
    { name: "Cultural Fest", date: "July 12", category: "cultural", seats: 5 },
    { name: "Health Camp", date: "July 19", category: "health", seats: 8 },
    { name: "Sports Meet", date: "August 15", category: "sports", seats: 0 },
    { name: "Food Festival", date: "August 22", category: "food", seats: 3 },
];

const destructureContainer = document.getElementById("destructureOutput");
events.forEach(function (ev) {
    const { name, date, category, seats } = ev;
    const card = document.createElement("div");
    card.className = "event-card";
    card.innerHTML = `<strong>${name}</strong><span class="badge">${category}</span> – ${date}, Seats: ${seats}`;
    destructureContainer.appendChild(card);
});

const cloned = [...events];
const available = cloned.filter(({ seats }) => seats > 0);
const spreadContainer = document.getElementById("spreadOutput");
available.forEach(function (ev) {
    const card = document.createElement("div");
    card.className = "event-card";
    card.innerHTML = `<strong>${ev.name}</strong> <span class="badge">${ev.category}</span> — ${ev.seats} seat(s) left`;
    spreadContainer.appendChild(card);
});

function formatEventLabel(name, category = "General", seats = 0) {
    return `Event: "${name}" | Category: ${category} | Seats: ${seats}`;
}

document.getElementById("defaultParamOutput").textContent = [
    formatEventLabel("Cultural Fest", "cultural", 5),
    formatEventLabel("Open Mic Night"),
    formatEventLabel("Health Talk", "health"),
].join("\n");
