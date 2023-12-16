export default function formatDate(dateString) {
  const options = { day: 'numeric', month: 'short', year: 'numeric', timeZone: 'Europe/Istanbul' };
  const date = new Date(dateString);
  return `${date.toLocaleDateString('en-US', options)}`;
}

export function formatTime(dateString) {
  const options = { hour: 'numeric', minute: 'numeric', hour12: false, timeZone: 'Europe/Istanbul' };
  const date = new Date(dateString);
  date.setMinutes(date.getMinutes() + date.getTimezoneOffset()); // Adjust for local time zone

  const hours = formatTwoDigits(date.getHours());
  const minutes = formatTwoDigits(date.getMinutes());

  return `${hours}:${minutes}`;
}

function formatTwoDigits(value) {
  return value < 10 ? `0${value}` : value;
}
