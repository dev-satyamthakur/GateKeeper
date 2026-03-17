# 📦 SDK (Client Library)

## Goal

Allow apps to use feature flags easily.

## Example

```javascript id="sdk001"
client.isEnabled("new_checkout", user)
```

## Responsibilities

* fetch flags
* cache locally
* evaluate features

## Features

* local cache
* periodic refresh
* fast evaluation

## Future

* streaming updates
* multi-language SDKs
