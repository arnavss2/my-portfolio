// Copyright 2020 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/**
 * Adds a random greeting to the page.
 */
function addRandomQuote() {
  const quotes =
      ['That\'s what she said', 'How you doin\'', 'Bazinga', 'I am the one who knocks'];

  // Pick a random quote.
  const quote = quotes[Math.floor(Math.random() * quotes.length)];

  // Add it to the page.
  const quoteContainer = document.getElementById('quote-container');
  quoteContainer.innerText = quote;
}

/** Fetches contacts from the server and adds them to the DOM. */
function loadContacts() {
    fetch('/list-contacts').then(response => response.json()).then((contacts) => {
      const contactListElement = document.getElementById('contact-list');
      contacts.forEach((contact) => {
        contactListElement.appendChild(createContactElement(contact));
      })
    });
}
  
  /** Creates an element that represents a contact, including its delete button. */
  function createContactElement(contact) {
    const contactElement = document.createElement('li');
    contactElement.className = 'contact';
  
    const nameElement = document.createElement('span');
    nameElement.innerText = contact.name;
  
    const emailElement = document.createElement('span');
    emailElement.innerText = contact.email;

    const messageElement = document.createElement('span');
    messageElement.innerText = contact.message;

    contactElement.appendChild(nameElement);
    contactElement.appendChild(emailElement);
    contactElement.appendChild(messageElement);

    return contactElement;
}