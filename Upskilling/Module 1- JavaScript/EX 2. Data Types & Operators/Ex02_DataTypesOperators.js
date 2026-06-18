const eventName = "Annual Cultural Fest";
const eventDate = "July 12, 2024";
let availableSeats = 10;

function renderInfo() {
    document.getElementById("eventName").textContent = eventName;
    document.getElementById("eventDate").textContent = eventDate;
    document.getElementById("seatCount").textContent = availableSeats;
    document.getElementById("summary").innerHTML =
        `<em>${`Event: ${eventName} | Date: ${eventDate} | Seats Left: ${availableSeats}`}</em>`;
}

function registerSeat() {
    if (availableSeats > 0) {
        availableSeats--;
        document.getElementById("seatInfo").textContent = "Seat registered successfully.";
    } else {
        document.getElementById("seatInfo").textContent = "No seats left!";
    }
    renderInfo();
}

function cancelSeat() {
    availableSeats++;
    document.getElementById("seatInfo").textContent = "Registration cancelled. Seat released.";
    renderInfo();
}

renderInfo();
