package com.mygdx.towerdefence.config;

import com.mygdx.towerdefence.gameactor.action.ActionType;
import com.mygdx.towerdefence.gameactor.priority.Priority;

import java.util.Map;

public class EnemyConfig {
    public int id;
    public int maxHealth;
    public int reward;
    public Priority priority;
    public float speed;
    public String name;
    public String SpriteName;
    public float actionRate;
    public float actionRange;
    public ActionType actionType;
    public Map<String, Float> actionParams;
}
