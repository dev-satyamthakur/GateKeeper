# ⚡ Real-Time Updates

## Goal

Instant propagation of flag changes.

## Design

```id="rt001"
Backend → Redis PubSub → Clients
```

## Flow

```id="rt002"
Flag updated
   ↓
Publish event
   ↓
Subscribers receive update
   ↓
Cache refreshed
```

## Tools

* Redis PubSub

## Future

* WebSockets
* GraphQL subscriptions
