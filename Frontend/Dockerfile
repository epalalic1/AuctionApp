FROM node:16 as build

WORKDIR /opt/app

RUN npm install -g @angular/cli@12

COPY package.json **/package-lock.json ./

RUN npm install

COPY . ./*

RUN ng build

EXPOSE 4200

CMD ["ng", "serve", "--host", "0.0.0.0"]