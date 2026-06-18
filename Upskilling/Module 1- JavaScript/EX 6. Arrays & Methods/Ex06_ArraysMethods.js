let events = [
    { name: "Cultural Fest", category: "cultural" },
    { name: "Live Music Night", category: "music" },
    { name: "Health Camp", category: "health" },
    { name: "Jazz in the Park", category: "music" },
    { name: "Baking Workshop", category: "workshop" },
    { name: "Sports Meet", category: "sports" },
];

function renderAll() {
    const container = document.getElementById("allEvents");
    container.innerHTML = "";
    events.forEach(function (ev) {
        const card = document.createElement("div");
        card.className = "event-card";
        card.innerHTML = `${ev.name} <span class="badge">${ev.category}</span>`;
        container.appendChild(card);
    });

    const musicList = events.filter(ev => ev.category === "music");
    const musicContainer = document.getElementById("musicEvents");
    musicContainer.innerHTML = "";
    musicList.forEach(function (ev) {
        const card = document.createElement("div");
        card.className = "event-card";
        card.textContent = ev.name;
        musicContainer.appendChild(card);
    });

    const formatted = events.map(ev => {
        const cap = ev.category.charAt(0).toUpperCase() + ev.category.slice(1);
        return `${cap}: ${ev.name}`;
    });
    const formContainer = document.getElementById("formattedEvents");
    formContainer.innerHTML = "";
    formatted.forEach(function (label) {
        const card = document.createElement("div");
        card.className = "event-card";
        card.textContent = label;
        formContainer.appendChild(card);
    });
}

function addNewEvent() {
    const name = document.getElementById("newEventName").value.trim();
    const category = document.getElementById("newCategory").value.trim().toLowerCase();
    if (!name || !category) return;
    events.push({ name, category });
    document.getElementById("newEventName").value = "";
    document.getElementById("newCategory").value = "";
    renderAll();
}

renderAll();
