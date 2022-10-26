package net.nergi.eternitywrapper

package object model {
  /** A specific version of [[Iterable]] that specialises in [[CommandLineOption]] instances. */
  trait OptionProducer extends Iterable[CommandLineOption]

  /** A way of separating the descriptions from their flag classes. */
  trait OptionDescription[O <: CommandLineOption] {
    val description: String
  }

  object Implicits {
    private def describe[O <: CommandLineOption](desc: String): OptionDescription[O] =
      new OptionDescription[O] {
        override val description: String = desc
      }

    // Descriptions taken from https://eternity.youfailit.net/wiki/List_of_command_line_parameters

    implicit val exec: OptionDescription[Exec] = describe(
      "Executes the indicated file as a console script at game startup."
    )

    implicit val nocscload: OptionDescription[NoCSCLoad.type] = describe(
      "Disables autoloaded console scripts for this play session only."
    )

    implicit val defparm: OptionDescription[DefParm.type] = describe(
      "Enables FPS ticker and screenshots with the F1 key."
    )

    implicit val deh: OptionDescription[Deh] = describe(
      "Loads one or more DeHackEd / BEX patch files."
    )

    implicit val dehout: OptionDescription[DehOut] = describe(
      "Enables verbose DeHackEd parser logging."
    )

    implicit val edf: OptionDescription[EDF] = describe(
      "Specifies the root EDF file."
    )

    implicit val edfenables: OptionDescription[EDFEnables.type] = describe(
      "Causes all gamemodes' definitions to be enabled. For compatibility with older projects."
    )
  }
}
