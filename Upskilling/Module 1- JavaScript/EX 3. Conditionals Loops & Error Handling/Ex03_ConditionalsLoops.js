const events = [
    { id: 1, name: "Cultural Fest", date: "2024-07-12", seats: 5, past: false },
    { id: 2, name: "Health Camp", date: "2024-07-19", seats: 0, past: false },
    { id: 3, name: "Plantation Drive", date: "2023-08-03", seats: 10, past: true },
    { id: 4, name: "Sports Meet", date: "2024-08-15", seats: 8, past: false },
    { id: 5, name: "Food Festival", date: "2024-08-22", seats: 3, past: false },
];

const listContainer = document.getElementById("eventList");
const messageBox = document.getElementById("message");

events.forEach(function (event) {
    if (event.past || event.seats <= 0) return;

    const card = document.createElement("div");
    card.className = "event-card";
    card.innerHTML = `
        <span class="badge">Upcoming</span>
        <h3>${event.name}</h3>
        <p>Date: ${event.date} &nbsp;|&nbsp; Seats Available: <strong>${event.seats}</strong></p>
        <button onclick="register(${event.id})">Register</button>
    `;
    listContainer.appendChild(card);
});

function register(id) {
    try {
        const event = events.find(e => e.id === id);
        if (!event) throw new Error("Event not found.");
        if (event.seats <= 0) throw new Error("No seats available for this event.");
        event.seats--;
        messageBox.className = "success";
        messageBox.textContent = `Successfully registered for "${event.name}". Seats remaining: ${event.seats}`;
        if (event.seats === 0) messageBox.textContent += " – This event is now full.";
    } catch (err) {
        messageBox.className = "error";
        messageBox.textContent = "Registration failed: " + err.message;
    }
}
