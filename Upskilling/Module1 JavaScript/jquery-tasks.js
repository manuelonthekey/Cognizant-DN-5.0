// Task 14: jQuery and JS Frameworks
// jQuery is loaded via CDN in index.html before this script

$(document).ready(function () {

  // $('#registerBtn').click() — handle Register button click
  $(document).on("click", "#registerBtn", function () {
    const card = $(this).closest(".event-card");
    const eventName = card.find("h4").text();
    console.log(`jQuery click: Register button clicked for "${eventName}"`);

    // .fadeOut() — hide the card smoothly on register
    card.fadeOut(400, function () {
      $(this).find(".seats-count").text("Registered!");
      // .fadeIn() — bring it back after a short delay to show confirmation
      $(this).delay(500).fadeIn(400);
    });
  });

  // .fadeIn() on page load — event cards animate in when DOM is ready
  $("#eventCardsContainer").hide().fadeIn(800);

  // .fadeOut() / .fadeIn() on category filter change
  $("#categoryFilter").on("change", function () {
    const container = $("#eventCardsContainer");
    container.fadeOut(200, function () {
      // renderEventCards() is defined in main.js and runs the re-render
      renderEventCards($("#categoryFilter").val(), $("#eventSearch").val());
      container.fadeIn(300);
    });
  });

  console.log("jQuery Tasks loaded.");

});

/*
  Task 14 — Benefit of React / Vue over plain JS + jQuery:

  React and Vue use a virtual DOM and component-based architecture.
  Instead of manually selecting elements and updating them (as we do with
  getElementById or jQuery's $(...)), the framework automatically re-renders
  only the parts of the UI that changed when data updates.

  Benefit: Declarative UI — you describe WHAT the UI should look like for a
  given state, and the framework handles HOW to update the DOM efficiently.
  This eliminates entire classes of bugs caused by out-of-sync DOM state and
  makes large, data-driven applications far easier to maintain and scale.
*/
