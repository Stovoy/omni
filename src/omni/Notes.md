# Flow

Arrange all raw data is arranged into Inputs.

Units position, type, hp, damage.
Structure position.
Mineral field amount.
Minerals stored.

Different pipelines then take this data, transform it, perform operations on top of it.

Worker pipeline
        Note: Base pipeline? need to also see if worker can be doing anything better, 
        like moving to a building location early)
Take in your own workers.
Take in mineral fields.
TODO: Take in your bases.
For each worker: compute
    distance to nearest mineral fields with 0 workers, 1 workers, 2 workers, etc.
    then compute which one is most efficient / closest (train coefficients?)
the trained computation step should just take distances and amount of workers (no internal batching)
decide which one is best
send output for that worker.

For transformations, need simple & fast ways to query the data.

How to deal with output collisions between different pipelines? need to resolve conflicts at end of computation, maybe by score.
when resources want to be used by an action (use this worker, use 50 minerals), they all need to be "attempted" and need to resolve by score.
how this works across many frames (for things like saving money) remains unclear.
probably need to change inputs as necessary. need a concept of "resource" that can be used.

for example, if a unit is dispatched to scout, but then the fighting module says that it should fight instead, and has a higher score,
can scouting module adapt? maybe it can provide multiple options. 