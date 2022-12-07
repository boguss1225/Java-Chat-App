# Client and Server Chat application in JAVA implementation

## Introduction
<p align='justify'> Simple chat application with multiple clients and single server view. Client can select their ID from the JList section. Upon the selection, the name on chat log will be differed. Client and server messages will be transferred to each other's message view. This implementation did not utilized socket. Instead, it used common model shared over client and server. </p>

## App Overview
![](screenshot.png)</br>
</br></br>

## Sequence Diagram

```mermaid
sequenceDiagram
Client -->> Model: register client view (init)
Server -->> Model: register server view (init)
Client ->> Model: Set Current User, Send message
Server ->> Model: Send message
Model -->> Model: append message
Model ->> Client: Send message
Model ->> Server: Send message
```

## Used Environment
- Windows 11
- JAVA : 1.8.0_351
- JRE : 17.0.4
