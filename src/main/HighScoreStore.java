package main;

import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;

/**
 *
 */
public class HighScoreStore
{
	private RecordStore store;
	
	private static final int MAX_SCORES = 5;

	private static final int NAME_SIZE = 10;
	private static final int SCORE_SIZE = 4;
	private static final int TOTAL_SIZE = NAME_SIZE + SCORE_SIZE;

	public HighScoreStore()
	{
		try
		{
			store = RecordStore.openRecordStore("MetalVsPopHighScores", true);
		}
		catch (RecordStoreException ex)
		{
			// error opening record store
		}
	}

	/**
	 * Adds a high score to the
	 * @return index of the new score, 1-5, or 0 if not in top 5 scores
	 */
	public int add(String name, int score)
	{
		// for now, just add to the record
		byte[] record = new byte[TOTAL_SIZE];

		int len = name.length();
		for (int i = 0; i < NAME_SIZE && i < len; i++)
		{
			record[i] = (byte) name.charAt(i);
		}

		String scoreString = Integer.toString(score);

		len = scoreString.length();
		for (int i = 0; i < SCORE_SIZE && i < len; i++)
		{
			record[i + NAME_SIZE] = (byte) scoreString.charAt(i);
		}

		try
		{
			store.addRecord(record, 0, record.length);
		}
		catch (RecordStoreException ex)
		{
			ex.printStackTrace();
		}

		
		// TODO: check place
		return 0;
	}

	/**
	 * Clears all records
	 */
	public void clear()
	{
		try
		{
			int len = store.getNumRecords();
			for (int i = 0; i < len; i++)
			{
				store.deleteRecord(i);
			}
		}
		catch (RecordStoreException ex)
		{
			// error reading record store
		}
	}

	public void close()
	{
		try
		{
			store.closeRecordStore();
		}
		catch (RecordStoreException ex)
		{
			// error closing record store
		}
	}
}
