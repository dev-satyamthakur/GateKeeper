# ⚡ Redis Caching Layer

## Goal

Reduce latency and remove DB dependency during evaluation.

## Design

```id="cache001"
App → Redis → evaluation
        ↓
       DB (fallback)
```

## Key Structure

### Keys

```id="cache002"
flag:{flag_name}
```

### Value

```json id="cache003"
{
  "enabled": true,
  "rollout": 20,
  "rules": []
}
```

## Cache Strategy

* Write-through cache
* Update cache on flag change

## Flow

```id="cache004"
Create/Update Flag
    ↓
DB update
    ↓
Redis update
```

## Future

* TTL
* cache invalidation strategies
