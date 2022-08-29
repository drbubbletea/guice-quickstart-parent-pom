# Vaadin Library

## Contents

### Widgets

#### Widget Adapter

Suppose we have some `User` class, and we want to retrieve a Vaadin component that represents a read-only
view, `UserViewWidget`.

How might we go about that? We'll need a `*Factory` class, `UserFactory`, to create it for us, and the caller will need
to inject that factory.

```
UserComponentFactory = ...; // injected in
User user = ...;
Component component = userComponentFactory.getView(user);
```

How does this grow with hundreds of source classes and hundreds of associated factories?

Is there an easier way?

This brings us our static `WidgetAdapter` class.

```

User user = ...;
Optional<Component> component = WidgetAdapter.adapt(user, WidgetPurpose.VIEW);
```
