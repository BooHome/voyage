package com.ihere.voyage.util.file;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class FileChangedReloadUtil {

	private static final Logger logger = LoggerFactory.getLogger(FileChangedReloadUtil.class);

	/** Constant for the default refresh delay. */
	private static final int DEFAULT_REFRESH_DELAY = 5000;

	private File file;

	/** The last time the configuration file was modified. */
	private long lastModified;

	/** The last time the file was checked for changes. */
	private long lastChecked;

	/** The minimum delay in milliseconds between checks. */
	private long refreshDelay = DEFAULT_REFRESH_DELAY;

	/** A flag whether a reload is required. */
	private boolean reloading;

	public FileChangedReloadUtil(File file) {
		this.file = file;
		init();
	}

	private void init() {
		updateLastModified();
	}

	public boolean reloadRequired() {
		if (!reloading) {
			long now = System.currentTimeMillis();

			if (now > lastChecked + refreshDelay) {
				lastChecked = now;
				if (hasChanged()) {
					logger.info("File change detected: {}", getName());
					reloading = true;
				}
			}
		}

		return reloading;
	}

	public void reloadPerformed() {
		updateLastModified();
	}

	/**
	 * Return the minimal time in milliseconds between two reloadings.
	 *
	 * @return the refresh delay (in milliseconds)
	 */
	public long getRefreshDelay() {
		return refreshDelay;
	}

	/**
	 * Set the minimal time between two reloadings.
	 *
	 * @param refreshDelay refresh delay in milliseconds
	 */
	public void setRefreshDelay(long refreshDelay) {
		this.refreshDelay = refreshDelay;
	}

	/**
	 * Update the last modified time.
	 */
	private void updateLastModified() {
		lastModified = file.lastModified();
		reloading = false;
	}

	/**
	 * Check if the configuration has changed since the last time it was loaded.
	 *
	 * @return a flag whether the configuration has changed
	 */
	private boolean hasChanged() {
		if (!file.exists()) {
			if (lastModified != 0) {
				logger.warn("File was deleted: {}", getName());
				lastModified = 0;
			}
			return false;
		}

		return file.lastModified() > lastModified;
	}

	private String getName() {
		return getName(file);
	}

	private String getName(File file) {
		return file.getAbsolutePath();
	}

}
