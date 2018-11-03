package de.einar.ecs.components;

import com.artemis.Component;

public class PowerUpComponent extends Component {

	public enum PowerUpType {
		SPEED,
		JUMP,
		HELM
	}

	public PowerUpType type;

}
