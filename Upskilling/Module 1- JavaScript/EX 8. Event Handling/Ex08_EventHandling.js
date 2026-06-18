const events = [
    { id: 1, name: "Cultural Fest", category: "cultural", date: "July 12" },
    { id: 2, name: "Health Camp", category: "health", date: "July 19" },
    { id: 3, name: "Sports Meet", category: "sports", date: "August 15" },
    { id: 4, name: "Food Festival", category: "food", date: "August 22" },
    { id: 5, name: "Jazz Night", category: "music", date: "September 1" },
];

const container = document.getElementById("eventContainer");
const searchBox = document.getElementById("searchBox");
const categorySelect = document.getElementById("categorySelect");

function renderEvents(list) {
    container.innerHTML = "";
    list.forEach(function (ev) {
        const card = document.createElement("div");
        card.className = "event-card";
        card.innerHTML = `
            <span class="badge">${ev.category}</span>
            <h3>${ev.name}</h3>
            <p>Date: ${ev.date}</p>
            <button onclick="handleRegister('${ev.name}')">Register</button>
        `;
        container.appendChild(card);
    });
}

function handleRegister(name) {
    alert("Registered for: " + name);
}

function applyFilters() {
    const query = searchBox.value.toLowerCase();
    const category = categorySelect.value;
    const filtered = events.filter(function (ev) {
        const matchName = ev.name.toLowerCase().includes(query);
        const matchCat = category === "all" || ev.category === category;
        return matchName && matchCat;
    });
    renderEvents(filtered);
}

categorySelect.onchange = applyFilters;
searchBox.addEventListener("keydown", function () { setTimeout(applyFilters, 50); });

renderEvents(events);
