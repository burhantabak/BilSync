export default function formatDate(dateString) {
    const options = { day: 'numeric', month: 'short', year: 'numeric' };
    const date = new Date(dateString);
    return `${date.toLocaleDateString('en-US', options)}`;
}
export function formatTime(dateString) {
    const date = new Date(dateString);
    const hours = formatTwoDigits(date.getHours());
    const minutes = formatTwoDigits(date.getMinutes());
  
    return `${hours}:${minutes}`;
  }
  
  function formatTwoDigits(value) {
    return value < 10 ? `0${value}` : value;
  }
  