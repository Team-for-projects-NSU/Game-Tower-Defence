package com.mygdx.towerdefence.gameactor;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;
import com.mygdx.towerdefence.config.BuildingConfig;
import com.mygdx.towerdefence.level.PathNode;
import com.mygdx.towerdefence.action.Action;

public class Building implements GameActor, Pool.Poolable {
    private final int id;
    private int health;
    private final int maxHealth;
    private final Priority priority;
    private final Vector2 position;
    private final String name;
    private final Action action;
    private final int demolitionCurrency;
    private boolean isActive;
    private float actionTimer;
    private float buildTimer;
    private GameActor target;
    private final ActorType actorType;

    public Building(BuildingConfig config, Action action, Vector2 position) {
        this.id = config.id;
        this.maxHealth = config.maxHealth;
        this.health = this.maxHealth;
        this.priority = config.priority;
        this.name = config.name;
        this.action = action;
        this.demolitionCurrency = config.demolitionCurrency;
        this.position = new Vector2(position);
        isActive = true;
        actionTimer = action.getRate();
        target = null;
        actorType = ActorType.Building;
    }

    public Building(BuildingConfig config, Action action) {
        this(config, action, Vector2.Zero);
    }

    public int applyDamage(int damage) {
        health -= damage;
        if (health < 0) health = 0;
        return  health;
    }

    @Override
    public int getHealthMax() {
        return maxHealth;
    }

    @Override
    public int getID() {
        return id;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public Priority getPriority() {
        return priority;
    }

    @Override
    public Vector2 getPosition() {
        return position;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Action getAction() {
        return action;
    }

    @Override
    public void reset() {
        this.health = maxHealth;
    }

    public int getDemolitionCurrency() {
        return demolitionCurrency;
    }

    @Override
    public void act(float delta) {
        if (buildTimer > 0) {
            buildTimer -= delta;
            return;
        }

        actionTimer -= delta;
        if (actionTimer <= 0) {
            action.call(this, delta, target);
            actionTimer = action.getRate();
        }
    }

    @Override
    public void kill() {
        isActive = false;
    }

    @Override
    public boolean isActive() {
        return isActive;
    }

    @Override
    public GameActor getTarget() {
        return target;
    }

    @Override
    public void setTarget(GameActor target) {
        this.target = target;
    }

    public void setBuildTime(float time) {
        buildTimer = time;
    }

    @Override
    public ActorType getType() {
        return actorType;
    }

    @Override
    public PathNode getCurrentNode() {
        return null;
    }
}
