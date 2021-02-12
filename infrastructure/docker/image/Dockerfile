FROM node:lts-slim

RUN npm install -g mastodon-bot
RUN mkdir /app

ENTRYPOINT ["mastodon-bot"]
CMD [ "/app/config.edn" ]