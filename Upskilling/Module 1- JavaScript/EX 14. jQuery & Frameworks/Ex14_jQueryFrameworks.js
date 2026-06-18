$(document).ready(function () {

    $("#showAllBtn").click(function () {
        $(".event-card").fadeIn(400);
    });

    $("#hideAllBtn").click(function () {
        $(".event-card").fadeOut(400);
    });

    $(document).on("click", ".reg-btn", function () {
        const eventName = $(this).data("event");
        const card = $(this).closest(".event-card");

        card.fadeOut(300, function () { card.fadeIn(300); });

        $("#confirmation")
            .text("You have registered for: " + eventName + ". See you there!")
            .fadeIn(400);

        setTimeout(function () { $("#confirmation").fadeOut(600); }, 3000);
    });

});
