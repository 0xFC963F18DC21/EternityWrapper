package net.nergi.eternitywrapper

package object model {
  /** A specific version of [[Iterable]] that specialises in [[CommandLineOption]] instances. */
  trait OptionProducer extends Iterable[CommandLineOption]

  /** A way of separating the descriptions from their flag classes. */
  trait OptionDescription[O <: CommandLineOption] {
    val description: String
  }

  /** Summonable representations of argument descriptions. Useful for making tooltips or similar
    * kinds of information pop-ups later.
    */
  object Descriptions {
    private def describe[O <: CommandLineOption](desc: String): OptionDescription[O] =
      new OptionDescription[O] {
        override val description: String = desc
      }

    // Descriptions taken from https://eternity.youfailit.net/wiki/List_of_command_line_parameters

    implicit val exec: OptionDescription[Exec] = describe(
      "Executes the indicated file as a console script at game startup."
    )

    implicit val noCscLoad: OptionDescription[NoCSCLoad.type] = describe(
      "Disables autoloaded console scripts for this play session only."
    )

    implicit val defParm: OptionDescription[DefParm.type] = describe(
      "Enables FPS ticker and screenshots with the F1 key."
    )

    implicit val deh: OptionDescription[Deh] = describe(
      "Loads one or more DeHackEd / BEX patch files."
    )

    implicit val dehOut: OptionDescription[DehOut] = describe(
      "Enables verbose DeHackEd parser logging."
    )

    implicit val edf: OptionDescription[EDF] = describe(
      "Specifies the root EDF file."
    )

    implicit val edfEnables: OptionDescription[EDFEnables.type] = describe(
      "Causes all gamemodes' definitions to be enabled. For compatibility with older projects."
    )

    implicit val edfOut: OptionDescription[EDFOut.type] = describe(
      "Enables verbose EDF logging to edfout##.txt."
    )

    implicit val edfShowWarnings: OptionDescription[EDFShowWarnings.type] = describe(
      "Displays verbose EDF warning information on the system console at startup."
    )

    implicit val dog: OptionDescription[Dog.type] = describe(
      "Enables a single MBF helper dog."
    )

    implicit val dogs: OptionDescription[Dogs] = describe(
      "Enables from one to three MBF helper dogs, depending on number specified."
    )

    implicit val episode: OptionDescription[Episode] = describe(
      "Start a new game on a given episode."
    )

    implicit val fast: OptionDescription[Fast.type] = describe(
      "Enables Nightmare! monster speed in any difficulty."
    )

    implicit val noMonsters: OptionDescription[NoMonsters.type] = describe(
      "Removes all monsters (except Lost Souls) from all levels."
    )

    implicit val respawn: OptionDescription[Respawn.type] = describe(
      "Enables Nightmare! monster respawning in any difficulty."
    )

    implicit val skill: OptionDescription[Skill] = describe(
      "Sets skill to level one through five."
    )

    implicit val soloNet: OptionDescription[SoloNet.type] = describe(
      "Starts the game as single-player cooperative (more monsters)."
    )

    implicit val spechit: OptionDescription[SpecHit] = describe(
      "Sets the spechit emulation magic number."
    )

    implicit val turbo: OptionDescription[Turbo] = describe(
      "Sets the player turbo factor."
    )

    implicit val warp: OptionDescription[Warp] = describe(
      "Starts the game at some episode + mission / map."
    )

    implicit val affinity: OptionDescription[Affinity] = describe(
      "Enables processor affinity mask to stop SDL_mixer crashes on multicore processors."
    )

    implicit val blockmap: OptionDescription[Blockmap.type] = describe(
      "Enables MBF blockmap builder for all levels."
    )

    implicit val grabMouse: OptionDescription[GrabMouse.type] = describe(
      "Enables mouse input grabbing."
    )

    implicit val noGrabMouse: OptionDescription[NoGrabMouse.type] = describe(
      "Disables mouse input grabbing."
    )

    implicit val numParticles: OptionDescription[NumParticles] = describe(
      "Sets the static particle limit."
    )

    implicit val speed: OptionDescription[Speed] = describe(
      "Sets the game speed to n% of normal speed. (10 \u2264 n \u2264 1000)."
    )

    implicit val statCopy: OptionDescription[StatCopy.type] = describe(
      "Does nothing. Enables external statistics driver in Vanilla Doom."
    )

    implicit val altDeath: OptionDescription[AltDeath.type] = describe(
      "Enables Deathmatch 2.0, where items respawn and weapons stay."
    )

    implicit val austinVirtualGaming: OptionDescription[AustinVirtualGaming.type] = describe(
      "Enables Austin Virtual Gaming-style Deathmatch, where levels end after 20 minutes."
    )

    implicit val deathmatch: OptionDescription[Deathmatch.type] = describe(
      "Enables original deathmatch mode."
    )

    implicit val dmFlags: OptionDescription[DmFlags] = describe(
      "Sets the dmflags variable to the given value."
    )

    implicit val extraTic: OptionDescription[ExtraTic.type] = describe(
      "[Possibly] duplicates one tic, making the game run at a fake 36 TPS."
    )

    implicit val frags: OptionDescription[Frags] = describe(
      "Sets the frag limit for Deathmatch. Levels end when this limit is reached."
    )

    implicit val net: OptionDescription[Net] = describe(
      "Specifies the peer-to-peer player node to use and the IP addresses of the other players."
    )

    implicit val port: OptionDescription[Port] = describe(
      "Specifies an alternative connection port."
    )

    implicit val Timer: OptionDescription[Timer] = describe(
      "Sets the Deathmatch time limit to the given amount of minutes."
    )

    implicit val triDeath: OptionDescription[TriDeath.type] = describe(
      "Enables SMMU Deathmatch 3.0, where barrels respawn and players drop backpacks (broken!)."
    )

    implicit val loadGame: OptionDescription[LoadGame] = describe(
      "Loads the savegame in slot n. (0 \u2264 n \u2264 7)."
    )

    implicit val save: OptionDescription[Save] = describe(
      "Sets the directory from / to which savegames are loaded and saved."
    )
  }
}
