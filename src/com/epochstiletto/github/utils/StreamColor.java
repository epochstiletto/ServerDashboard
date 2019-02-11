package com.epochstiletto.github.utils;

/**
 * Enum for coloring the output stream.
 * 
 * <p>
 * If you are to use this, make sure you...
 * <p>
 * <li>space strings accordingly, they are not spaced automatically.
 * <li>use StreamColor.RESET after every message.<br>
 * <br>
 * <p>
 * You need to use RESET because otherwise, if you use more than one color, the
 * colors alternate between each other. Example:
 * <p>
 * <br>
 * <code>
 * text = RED + "first " + GREEN + "second"
 * </code>
 * <p>
 * would print as:
 * <p>
 * <font color="red"> first</font> <font color="green"> second</font>
 * <p>
 * as instructed.
 * <p>
 * If you were to print that again, it would output:
 * <p>
 * <font color="green"> first</font> <font color="red">second</font>
 * 
 * @author Stile
 *
 */
public enum StreamColor {

	BLACK {
		@Override
		public String toString() {
			return (char) 27 + "[30m";
		}

	},
	LIGHT_RED {
		@Override
		public String toString() {
			return (char) 27 + "[31;1m";
		}
	},
	RED {

		@Override
		public String toString() {
			return (char) 27 + "[31m";
		}

	},
	GREEN {

		@Override
		public String toString() {
			return (char) 27 + "[32m";
		}

	},
	LIGHT_YELLOW {
		public String toString() {
			return (char) 27 + "[33;1m";
		}
	},
	YELLOW {

		@Override
		public String toString() {
			return (char) 27 + "[33m";
		}

	},
	BLUE {

		@Override
		public String toString() {
			return (char) 27 + "[34m";
		}

	},
	LIGHT_BLUE {
		@Override
		public String toString() {
			return (char) 27 + "[34;1m";
		}
	},
	MAGENTA {

		@Override
		public String toString() {
			return (char) 27 + "[35m";
		}

	},
	LIGHT_CYAN {
		@Override
		public String toString() {
			return (char) 27 + "[36;1m";
		}
	},
	CYAN {

		@Override
		public String toString() {
			return (char) 27 + "[36m";
		}

	},
	WHITE {

		@Override
		public String toString() {
			return (char) 27 + "[37m";
		}

	},
	RESET {

		@Override
		public String toString() {
			return (char) 27 + "[0m";
		}
	};
}
