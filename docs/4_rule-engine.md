# 🧩 Rule Engine

## Goal

Enable targeted feature rollouts.

## Rule Types

```id="rule001"
country == IN
device == IOS
email contains domain
```

## Design

### Interface

```id="rule002"
Rule {
   matches(user): Boolean
}
```

### Implementations

```id="rule003"
CountryRule
DeviceRule
EmailRule
```

## Evaluation Flow

```id="rule004"
if rule matches → ENABLE
else → fallback to rollout
```

## Storage

Table: flag_rules

```id="rule005"
attribute
operator
value
```

## Future

* AND/OR logic
* nested rules
