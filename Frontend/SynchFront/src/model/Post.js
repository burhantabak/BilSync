export default class Post {
    constructor(userName, title, description, vote,isTrading,isLostnFound, price, IBAN, comments) {
      this.userName = userName;
      this.description = description;
      this.isTrading = isTrading;
      this.isLostnFound = isLostnFound;
      this.price = price;
      this.IBAN = IBAN;
      this.vote = vote;
      this.title = title;
      this.comments = comments;
    }
  }