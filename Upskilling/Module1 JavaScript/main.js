console.log("Welcome to the Community Portal");

// Alert when page is fully loaded
window.addEventListener("load", function () {
  alert("Page fully loaded! Welcome to the Community Portal.");
});

// Task 2: Syntax, Data Types, and Operators
const eventName = "Community Cleanup";
const eventDate = "2026-07-15";
let availableSeats = 50;

console.log(`Event: ${eventName} | Date: ${eventDate} | Seats Available: ${availableSeats}`);

// Task 3: Conditionals, Loops, and Error Handling
const events = [
  { name: "Community Cleanup", date: "2026-07-15", seats: 50 },
  { name: "Summer Festival",   date: "2026-08-10", seats: 0  },
  { name: "Health Camp",       date: "2025-05-01", seats: 20 },
  { name: "Book Fair",         date: "2026-09-05", seats: 30 },
];

const today = new Date();

console.log("Valid upcoming events:");
events.forEach(function (event) {
  const eventDateObj = new Date(event.date);
  if (event.seats > 0 && eventDateObj > today) {
    console.log(`  ✔ ${event.name} on ${event.date} — ${event.seats} seats left`);
  } else {
    console.log(`  ✘ ${event.name} — not shown (past or full)`);
  }
});

// Task 4: Functions, Scope, Closures, Higher-Order Functions

// Adds a new event object to the events array
function addEvent(name, date, seats, category) {
  const newEvent = { name, date, seats, category };
  events.push(newEvent);
  console.log(`Event added: ${name} (${category})`);
  return newEvent;
}

// Closure — returns a registrar function that tracks count per category
function createCategoryRegistrar(category) {
  let totalRegistrations = 0;
  return function (userName) {
    totalRegistrations++;
    console.log(`${userName} registered for category "${category}". Total: ${totalRegistrations}`);
    return totalRegistrations;
  };
}

// Registers a user against a specific event by name
function registerUser(userName, eventName) {
  const target = events.find(function (e) { return e.name === eventName; });
  if (!target) {
    console.error(`Event "${eventName}" not found.`);
    return;
  }
  if (target.seats <= 0) {
    console.error(`"${eventName}" is fully booked.`);
    return;
  }
  target.seats--;
  console.log(`${userName} registered for "${eventName}". Seats left: ${target.seats}`);
}

// Higher-order function — filters events using a callback predicate
function filterEventsByCategory(category, callback) {
  return events.filter(function (event) {
    return event.category === category && callback(event);
  });
}

// --- Demo calls (logged to console) ---
addEvent("Music Night", "2026-10-20", 100, "entertainment");
addEvent("City Workshop", "2026-11-05", 40, "workshop");

const registerForWorkshop = createCategoryRegistrar("workshop");
registerForWorkshop("Alice");
registerForWorkshop("Bob");

registerUser("Charlie", "Music Night");

const upcomingWorkshops = filterEventsByCategory("workshop", function (event) {
  return new Date(event.date) > today && event.seats > 0;
});
console.log("Upcoming workshops:", upcomingWorkshops.map(function (e) { return e.name; }));

// Task 5: Objects and Prototypes

function CommunityEvent(name, date, seats, category) {
  this.name     = name;
  this.date     = date;
  this.seats    = seats;
  this.category = category;
}

CommunityEvent.prototype.checkAvailability = function () {
  const upcoming = new Date(this.date) > new Date();
  if (this.seats > 0 && upcoming) {
    return `"${this.name}" is available — ${this.seats} seats left.`;
  } else if (this.seats <= 0) {
    return `"${this.name}" is fully booked.`;
  } else {
    return `"${this.name}" has already passed.`;
  }
};

const sampleEvent = new CommunityEvent("Community Cleanup", "2026-07-15", 50, "cleanup");
console.log(sampleEvent.checkAvailability());

console.log("Event properties:");
Object.entries(sampleEvent).forEach(function (entry) {
  console.log(`  ${entry[0]}: ${entry[1]}`);
});

// Task 6: Arrays and Methods

// .push() — add new events to the list
events.push({ name: "Jazz Evening",    date: "2026-08-20", seats: 60, category: "music" });
events.push({ name: "Rock Fest",       date: "2026-09-15", seats: 80, category: "music" });
events.push({ name: "Baking Workshop", date: "2026-10-01", seats: 25, category: "workshop" });
console.log(`Total events after push: ${events.length}`);

// .filter() — show only music events
const musicEvents = events.filter(function (event) {
  return event.category === "music";
});
console.log("Music events:", musicEvents.map(function (e) { return e.name; }));

// .map() — format display cards
const displayCards = events.map(function (event) {
  const label = event.category.charAt(0).toUpperCase() + event.category.slice(1);
  return `${label} on ${event.name}`;
});
console.log("Event display cards:");
displayCards.forEach(function (card) { console.log(`  - ${card}`); });

// Task 7: DOM Manipulation

function renderEventCards(categoryFilter, searchTerm) {
  const container = document.querySelector("#eventCardsContainer");
  if (!container) return;
  container.innerHTML = "";

  let filtered = events.filter(function (e) {
    return new Date(e.date) > new Date() && e.seats > 0;
  });

  if (categoryFilter && categoryFilter !== "all") {
    filtered = filtered.filter(function (e) { return e.category === categoryFilter; });
  }

  if (searchTerm) {
    const term = searchTerm.toLowerCase();
    filtered = filtered.filter(function (e) { return e.name.toLowerCase().includes(term); });
  }

  if (filtered.length === 0) {
    container.textContent = "No events match your search.";
    return;
  }

  filtered.forEach(function (event) {
    const card = document.createElement("div");
    card.className = "event-card";

    const title = document.createElement("h4");
    title.textContent = event.name;

    const info = document.createElement("p");
    info.textContent = `Date: ${event.date} | Seats: ${event.seats}`;

    const seatsDisplay = document.createElement("span");
    seatsDisplay.className = "seats-count";
    seatsDisplay.textContent = `${event.seats} seats left`;

    const registerBtn = document.createElement("button");
    registerBtn.type = "button";
    registerBtn.textContent = "Register";
    // onclick: register user and update UI
    registerBtn.addEventListener("click", function () {
      if (event.seats <= 0) {
        seatsDisplay.textContent = "Fully booked";
        registerBtn.disabled = true;
        return;
      }
      event.seats--;
      seatsDisplay.textContent = `${event.seats} seats left`;
      registerBtn.textContent = "Registered \u2714";
      registerBtn.disabled = true;
      cancelBtn.disabled = false;
      console.log(`Registered for "${event.name}". Seats left: ${event.seats}`);
    });

    const cancelBtn = document.createElement("button");
    cancelBtn.type = "button";
    cancelBtn.textContent = "Cancel";
    cancelBtn.disabled = true;
    cancelBtn.addEventListener("click", function () {
      event.seats++;
      seatsDisplay.textContent = `${event.seats} seats left`;
      registerBtn.textContent = "Register";
      registerBtn.disabled = false;
      cancelBtn.disabled = true;
      console.log(`Cancelled registration for "${event.name}". Seats left: ${event.seats}`);
    });

    card.appendChild(title);
    card.appendChild(info);
    card.appendChild(seatsDisplay);
    card.appendChild(registerBtn);
    card.appendChild(cancelBtn);
    container.appendChild(card);
  });
}

renderEventCards();

// Task 8: Event Handling

// onchange — filter cards by selected category
const categorySelect = document.querySelector("#categoryFilter");
if (categorySelect) {
  categorySelect.addEventListener("change", function () {
    const selected = categorySelect.value;
    const search = document.querySelector("#eventSearch") ? document.querySelector("#eventSearch").value : "";
    console.log(`Category filter changed: ${selected}`);
    renderEventCards(selected, search);
  });
}

// keydown — live search cards by event name
const eventSearch = document.querySelector("#eventSearch");
if (eventSearch) {
  eventSearch.addEventListener("keydown", function () {
    // Use setTimeout so the input value is read after the key updates it
    setTimeout(function () {
      const term = eventSearch.value;
      const cat = categorySelect ? categorySelect.value : "all";
      console.log(`Search keydown: "${term}"`);
      renderEventCards(cat, term);
    }, 0);
  });
}

// Task 9: Async JS, Promises, Async/Await

const spinner    = document.querySelector("#loadingSpinner");
const fetchStatus = document.querySelector("#fetchStatus");

function showSpinner() { if (spinner) spinner.style.display = "block"; }
function hideSpinner() { if (spinner) spinner.style.display = "none"; }

// Approach 1: fetch with .then() and .catch()
function fetchEventsWithPromise() {
  showSpinner();
  fetchStatus.textContent = "Loading events...";

  fetch("./events.json")
    .then(function (response) {
      if (!response.ok) throw new Error(`HTTP error: ${response.status}`);
      return response.json();
    })
    .then(function (data) {
      hideSpinner();
      fetchStatus.textContent = `Loaded ${data.events.length} events from API (Promise).`;
      console.log("Promise fetch result:", data.events);
    })
    .catch(function (error) {
      hideSpinner();
      fetchStatus.textContent = "Failed to load events (Promise).";
      console.error("Fetch error (Promise):", error.message);
    });
}

// Approach 2: fetch with async/await
async function loadEventsAsync() {
  showSpinner();
  fetchStatus.textContent = "Loading events...";

  try {
    const response = await fetch("./events.json");
    if (!response.ok) throw new Error(`HTTP error: ${response.status}`);

    const data = await response.json();
    hideSpinner();
    fetchStatus.textContent = `Loaded ${data.events.length} events from API (async/await).`;
    console.log("Async/await fetch result:", data.events);

    data.events.forEach(function (fetched) {
      const exists = events.find(function (e) { return e.name === fetched.name; });
      if (!exists) events.push(fetched);
    });

    renderEventCards();
  } catch (error) {
    hideSpinner();
    fetchStatus.textContent = "Failed to load events. Showing local data.";
    console.error("Fetch error (async/await):", error.message);
    renderEventCards();
  }
}

loadEventsAsync();

// Task 10: Modern JavaScript Features

// Default parameters — category defaults to "all", maxResults to 10
function getFilteredEvents(category = "all", maxResults = 10) {
  // Spread operator — clone the array before filtering to avoid mutating the original
  const eventsCopy = [...events];

  const filtered = eventsCopy.filter(function (event) {
    return category === "all" || event.category === category;
  });

  return filtered.slice(0, maxResults);
}

// Destructuring — extract event details from each object
function logEventDetails(event) {
  const { name, date, seats, category = "general" } = event;
  console.log(`[${category.toUpperCase()}] ${name} | Date: ${date} | Seats: ${seats}`);
}

const upcomingAll = getFilteredEvents();
console.log("Task 10 — All events (spread + default params):");
upcomingAll.forEach(logEventDetails);

const musicOnly = getFilteredEvents("music", 5);
console.log("Task 10 — Music events only:");
musicOnly.forEach(logEventDetails);

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

// Task 11: Working with Forms

function clearFieldErrors() {
  ["nameError", "emailError", "dateError", "eventTypeError"].forEach(function (id) {
    const el = document.getElementById(id);
    if (el) el.textContent = "";
  });
  ["residentName", "residentEmail", "eventDate", "eventType"].forEach(function (id) {
    const el = document.getElementById(id);
    if (el) el.classList.remove("input-error");
  });
}

function showFieldError(inputId, errorId, message) {
  const input = document.getElementById(inputId);
  const error = document.getElementById(errorId);
  if (input) input.classList.add("input-error");
  if (error) error.textContent = message;
}

function validateRegistrationForm(form) {
  clearFieldErrors();
  let isValid = true;

  // Access fields via form.elements
  const { residentName, residentEmail, eventDate, eventType } = form.elements;

  if (!residentName.value.trim()) {
    showFieldError("residentName", "nameError", "Name is required.");
    isValid = false;
  }

  const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
  if (!residentEmail.value.trim()) {
    showFieldError("residentEmail", "emailError", "Email is required.");
    isValid = false;
  } else if (!emailPattern.test(residentEmail.value)) {
    showFieldError("residentEmail", "emailError", "Enter a valid email address.");
    isValid = false;
  }

  if (!eventDate.value) {
    showFieldError("eventDate", "dateError", "Please select an event date.");
    isValid = false;
  } else if (new Date(eventDate.value) < new Date()) {
    showFieldError("eventDate", "dateError", "Event date must be in the future.");
    isValid = false;
  }

  if (!eventType.value) {
    showFieldError("eventType", "eventTypeError", "Please select an event type.");
    isValid = false;
  }

  return isValid;
}

registrationForm.addEventListener("submit", function (event) {
  event.preventDefault();

  if (!validateRegistrationForm(registrationForm)) return;

  isFormSubmitted = true;

  try {
    const { residentName, eventType } = registrationForm.elements;
    const name = residentName.value.trim();
    const selectedEvent = eventType.options[eventType.selectedIndex].text;

    if (availableSeats <= 0) throw new Error("No seats available for this event.");

    availableSeats--;
    clearFieldErrors();
    console.log("Registration submitted:", { name, selectedEvent });
    console.log(`Seats remaining: ${availableSeats}`);

    // Task 12: POST to mock API
    submitRegistration({ name, email: registrationForm.elements.residentEmail.value.trim(), event: selectedEvent });

  } catch (error) {
    console.error("Registration error:", error.message);
    confirmationMessage.value = `Registration failed: ${error.message}`;
  }
});

// Task 12: AJAX & Fetch API

// Simulates a delayed server response using setTimeout inside a Promise
function mockServerPost(payload) {
  return new Promise(function (resolve, reject) {
    setTimeout(function () {
      // Simulate 90% success rate
      if (Math.random() > 0.1) {
        resolve({ status: 201, message: "Registration saved successfully." });
      } else {
        reject(new Error("Server error: please try again."));
      }
    }, 1500);
  });
}

async function submitRegistration(payload) {
  const statusEl = document.getElementById("submitStatus");
  const submitBtn = registrationForm.querySelector("button[type='submit']");

  statusEl.textContent = "Submitting...";
  statusEl.className = "submit-status pending";
  submitBtn.disabled = true;

  try {
    // fetch() POST to public mock REST API
    const response = await fetch("https://jsonplaceholder.typicode.com/posts", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(payload),
    });

    if (!response.ok) throw new Error(`Server responded with ${response.status}`);

    const result = await response.json();
    console.log("API response:", result);

    // setTimeout to simulate processing delay before showing message
    await mockServerPost(payload);

    statusEl.textContent = `✔ Registration confirmed for ${payload.name} (ID: ${result.id}).`;
    statusEl.className = "submit-status success";
    confirmationMessage.value = `Thank you, ${payload.name}. Your registration for "${payload.event}" was received.`;
  } catch (error) {
    console.error("Submission error:", error.message);
    statusEl.textContent = `✘ Submission failed: ${error.message}`;
    statusEl.className = "submit-status error";
  } finally {
    submitBtn.disabled = false;
  }
}

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
    }
  );
}

// Task 13: Debugging and Testing

// Logs full form state — call debugFormState() in DevTools Console anytime
function debugFormState() {
  const form = document.getElementById("registrationForm");
  if (!form) return;

  console.group("Form Debug State");
  console.log("Form valid:", form.checkValidity());

  const fields = Array.from(form.elements)
    .filter(function (el) { return el.name; })
    .map(function (el) { return { name: el.name, value: el.value, type: el.type }; });

  console.table(fields);
  console.groupEnd();
}

// Wraps fetch POST with timing and payload logging
async function debugFetchPost(payload) {
  console.group("Fetch POST Debug");
  console.log("Payload being sent:", payload);
  console.time("fetch-duration");

  // To pause here: open DevTools → Sources → main.js → click the line below's number
  // debugger;

  try {
    const response = await fetch("https://jsonplaceholder.typicode.com/posts", {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(payload),
    });
    const result = await response.json();
    console.timeEnd("fetch-duration");
    console.log("Response status:", response.status);
    console.log("Response body:", result);
    console.groupEnd();
    return result;
  } catch (error) {
    console.timeEnd("fetch-duration");
    console.error("Fetch failed:", error.message);
    console.groupEnd();
    throw error;
  }
}

// DevTools quick-reference (comments only — no runtime effect):
// Console tab → type debugFormState() → Enter to inspect all fields live
// Network tab → submit form → click the POST → check Payload and Response tabs
// Sources tab → open main.js → click line number = breakpoint
//             → F10 step over | F11 step into | Shift+F11 step out
//             → hover any variable or check Scope panel on the right
