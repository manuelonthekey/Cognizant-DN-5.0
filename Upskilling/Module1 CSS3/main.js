console.log("Welcome to the Community Portal");

window.addEventListener("load", function () {
  alert("The Community Portal page has fully loaded.");
});

const registrationForm = document.getElementById("registrationForm");
const eventTypeSelect = document.getElementById("eventType");
const confirmationMessage = document.getElementById("confirmationMessage");
const trackedForms = document.querySelectorAll("form");
let isFormStarted = false;
let isFormSubmitted = false;

trackedForms.forEach(function (form) {
  form.addEventListener("input", function () {
    isFormStarted = true;
  });

  form.addEventListener("change", function () {
    isFormStarted = true;
  });
});

const savedEventType = localStorage.getItem("preferredEventType");

console.log("Community Event Portal script loaded.");

if (savedEventType) {
  eventTypeSelect.value = savedEventType;
  console.log("Loaded preferred event type:", savedEventType);
}

registrationForm.addEventListener("submit", function (event) {
  event.preventDefault();
  isFormSubmitted = true;

  const name = document.getElementById("residentName").value;
  const eventType = document.getElementById("eventType");
  const selectedEvent = eventType.options[eventType.selectedIndex].text;

  console.log("Registration submitted:", {
    name: name,
    selectedEvent: selectedEvent,
  });
  confirmationMessage.value = `Thank you, ${name}. Your registration for ${selectedEvent} has been received.`;
});

function saveEventPreference() {
  localStorage.setItem("preferredEventType", eventTypeSelect.value);
  sessionStorage.setItem("lastSelectedEventType", eventTypeSelect.value);
  console.log("Saved event preference:", eventTypeSelect.value);
}

function clearPreferences() {
  localStorage.clear();
  sessionStorage.clear();
  eventTypeSelect.value = "";
  console.log("Cleared localStorage and sessionStorage.");
  confirmationMessage.value = "Saved preferences have been cleared.";
}

function validatePhoneNumber() {
  const phoneNumber = document.getElementById("phoneNumber").value;
  const phoneMessage = document.getElementById("phoneMessage");
  const phonePattern = /^[0-9]{10}$/;
  const isValidPhone = phonePattern.test(phoneNumber);

  console.log("Phone validation result:", isValidPhone);
  phoneMessage.textContent = isValidPhone
    ? "Valid phone number."
    : "Please enter a valid 10 digit phone number.";
}

function showEventFee() {
  const feedbackEvent = document.getElementById("feedbackEvent");
  const eventFee = document.getElementById("eventFee");
  const selectedFee = feedbackEvent.value || "0";

  console.log("Selected feedback event fee:", selectedFee);
  eventFee.textContent = `Selected event fee: $${selectedFee}`;
}

function showFeedbackConfirmation() {
  const feedbackConfirmation = document.getElementById("feedbackConfirmation");

  isFormSubmitted = true;
  console.log("Feedback submitted.");
  feedbackConfirmation.value = "Thank you for sharing your feedback.";
}

function enlargeImage(image) {
  image.classList.toggle("enlarged");
  console.log("Image enlarged state:", image.classList.contains("enlarged"));
}

function countFeedbackCharacters() {
  const feedbackMessage = document.getElementById("feedbackMessage");
  const characterCount = document.getElementById("characterCount");
  const typedCharacters = feedbackMessage.value.length;

  console.log("Feedback character count:", typedCharacters);
  characterCount.textContent = `Characters typed: ${typedCharacters}`;
}

function showVideoReadyMessage() {
  console.log("Promo video can play.");
  document.getElementById("videoMessage").textContent = "Video ready to play";
}

function warnUnfinishedForm(event) {
  if (isFormStarted && !isFormSubmitted) {
    event.preventDefault();
    event.returnValue =
      "You have unfinished form changes. Are you sure you want to leave?";
    return event.returnValue;
  }
}

function findNearbyEvents() {
  const locationMessage = document.getElementById("locationMessage");

  if (!navigator.geolocation) {
    locationMessage.textContent =
      "Geolocation is not supported by this browser.";
    return;
  }

  locationMessage.textContent = "Finding your location...";
  console.log("Requesting geolocation with high accuracy.");

  navigator.geolocation.getCurrentPosition(
    function (position) {
      const latitude = position.coords.latitude.toFixed(6);
      const longitude = position.coords.longitude.toFixed(6);

      console.log("Geolocation success:", {
        latitude: latitude,
        longitude: longitude,
      });
      locationMessage.textContent = `Your coordinates are Latitude: ${latitude}, Longitude: ${longitude}. Nearby event search is ready.`;
    },
    function (error) {
      console.log("Geolocation error:", error.code);
      if (error.code === error.PERMISSION_DENIED) {
        locationMessage.textContent =
          "Location permission was denied. Please allow location access to find nearby events.";
      } else if (error.code === error.TIMEOUT) {
        locationMessage.textContent =
          "Location request timed out. Please try again.";
      } else {
        locationMessage.textContent =
          "Unable to retrieve your location right now.";
      }
    },
    {
      enableHighAccuracy: true,
      timeout: 10000,
      maximumAge: 0,
    },
  );
}
