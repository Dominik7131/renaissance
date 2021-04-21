package org.renaissance;

import org.renaissance.core.DirUtils;

import java.nio.file.Path;

/**
 * Represents a benchmark execution context. Allows a benchmark to access
 * harness-provided utility services, such as retrieving benchmark- and
 * configuration-specific parameter values, JAR file resources, or selected
 * filesystem operations.
 */
public interface BenchmarkContext {

  /**
   * Returns the value of the given named parameter in the currently selected
   * benchmark configuration as {@code int}. The method fails (with an
   * exception) if the parameter does not exist or cannot be converted to the
   * desired type.
   *
   * @param name Parameter name.
   * @return Parameter value as {@code int} value.
   */
  int intParameter(final String name);

  /**
   * Returns the value of the given named parameter in the currently selected
   * benchmark configuration as {@code double}. The method fails (with an
   * exception) if the parameter does not exist or cannot be converted to the
   * desired type.
   *
   * @param name Parameter name.
   * @return Parameter value as {@code double} value.
   */
  double doubleParameter(final String name);

  /**
   * Returns the value of the given named parameter in the currently selected
   * benchmark configuration as {@link String}. The method fails (with an
   * exception) if the parameter does not exist.
   *
   * @param name Parameter name.
   * @return Parameter value as {@code double} value.
   */
  String stringParameter(final String name);

  /**
   * Provides a path to the benchmark-specific scratch directory that will be
   * deleted (or not, depending on harness settings) after the harness exits.
   * The benchmark can do whatever it wants in the subtree below the scratch
   * directory, but should not modify anything outside that subtree.
   *
   * @return {@link Path} to benchmark-specific scratch directory.
   */
  Path scratchDirectory();

  //
  // File system operations
  //
  @Deprecated
  default Path generateTempDir(String name) {
    return DirUtils.generateTempDir(name);
  }

  @Deprecated
  default void deleteTempDir(Path dir) {
    DirUtils.deleteTempDir(dir);
  }

}
