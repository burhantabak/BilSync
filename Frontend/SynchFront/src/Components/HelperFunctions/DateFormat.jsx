export default function formatDate(dateString) {
    const options = { day: 'numeric', month: 'short', year: 'numeric', timeZone: 'Europe/Istanbul' };
    const date = new Date(dateString);
    return `${date.toLocaleDateString('en-US', options)}`;
}
export function formatTime(dateString) {
  const options = { hour: 'numeric', minute: 'numeric', hour12: false, timeZone: 'Europe/Istanbul' };
  const date = new Date(dateString);
  return date.toLocaleTimeString('en-US', options);
  }
  
  function formatTwoDigits(value) {
    return value < 10 ? `0${value}` : value;
  }
  