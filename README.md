# Auction App

Auction App is a web application that allows users to register and log in, then put products up for auction and bid on other products. Each registered user has an overview of all the products he has put up for auction, as well as an overview of all the products for which he has made an offer. Registered users can change the data they provided during registration at any time they wish, as well as deactivate their profile.
When viewing each of the products, registered users will be able to distinguish products that have been auctioned from those that have not. When viewing all products, the user can filter products based on category and subcategory, as well as sort based on certain criteria such as auction expiration date, prices, and the like.
Every time another user places a new highest bid for a product, all other users who have placed a lower bid than the current highest bid receive a notification with a link to that product. The highest bidder for a product whose auction has expired gets the option to pay for the product by entering their card details.

## Environment Variables

To run this project, you will need to add the following environment variables to your .env file

`API_KEY`

`ANOTHER_API_KEY`


## Demo

Insert gif or link to demo


## Run Locally

Clone the project

```bash
  git clone https://github.com/epalalic1/AuctionApp.git
```

Go to the project directory

```bash
  cd AuctionApp/Frontend
```

Install dependencies

```bash
  npm install
```

Start the server

```bash
  npm run start ng serve --proxy-config proxy.config.json
```
