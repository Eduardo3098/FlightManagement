ARG IMAGE
FROM $IMAGE

RUN addgroup -S bbe && adduser -S bbe -G bbe
RUN chown -R bbe /usr/share/nginx/ && chown -R bbe /var/cache/nginx/ && chown -R bbe /etc/nginx/ && chown -R bbe /var/log/nginx/

RUN touch /var/run/nginx.pid && \
        chown -R bbe:bbe /var/run/nginx.pid

USER bbe

COPY ./build/ /usr/share/nginx/html