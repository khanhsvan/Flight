function getNotifications() {
  var xhr = new XMLHttpRequest();
  xhr.open("GET", "/getNotifications");
  xhr.setRequestHeader("Content-Type", "application/json");
  xhr.onreadystatechange = function() {
    if (xhr.readyState === 4 && xhr.status === 200) {
      var response = JSON.parse(xhr.responseText);
      createPopup(response.message);
    }
  };
  xhr.send();
}

function createPopup(message) {
  var popup = window.open("", "popup", "width=400,height=200");
  popup.document.write("<p>" + message + "</p>");
}
