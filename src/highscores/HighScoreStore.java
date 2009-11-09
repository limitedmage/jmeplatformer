package highscores;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Vector;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;

/**
 * A class for storing high scores.
 * Only the top 5 scores are stored.
 *
 */
public class HighScoreStore
{
	private RecordStore store;
	private Vector scores;
	private RecordEnumeration enumerator;
	
	public static final int MAX_SCORES = 5;

	public static final int NAME_SIZE = 10;
	
	private static final int POINTS_SIZE = 4;
	private static final int TOTAL_SIZE = NAME_SIZE + POINTS_SIZE;

	/**
	 * Opens a record store and loads its data
	 */
	public HighScoreStore()
	{
		this.scores = new Vector();

		try
		{
			this.store = RecordStore.openRecordStore("MetalVsPopHighScores", true);
			this.enumerator = this.store.enumerateRecords(null, null, true);
		}
		catch (RecordStoreException ex)
		{
			System.out.println("Error opening scores");
		}

		this.load();
	}

	/**
	 * Saves the high scores
	 */
	public void save()
	{
		// clears the record store
		this.clear();

		// adds each score to the record

		Score score;
		
		int size = scores.size();

		for (int i = 0; i < size; i++)
		{
			score = (Score)scores.elementAt(i);

			byte[] record = new byte[TOTAL_SIZE];
			
			HighScoreStore.packName(score, record);
			HighScoreStore.packPoints(score, record);

			try
			{
				store.addRecord(record, 0, record.length);
			}
			catch (RecordStoreException ex)
			{
				System.out.println("Error saving scores");
			}
		}

	}

	/**
	 * Loads the record store data
	 */
	public void load()
	{
		this.fillVector();		
	}

	/**
	 * Return the number of scores stored
	 * @return Number of scores stored
	 */
	public int size()
	{
		return this.scores.size();
	}

	/**
	 * Return the score at the specified index
	 * @param index - The index to search for
	 * @return A string representing the high score stored at that index
	 */
	public String elementAt(int index)
	{
		return this.scores.elementAt(index).toString();
	}

	/**
	 * Clears all records in the RecordStore (does not delete them from the vector)
	 */
	public void clear()
	{
		try
		{
			this.enumerator.reset();
			while (this.enumerator.hasNextElement())
			{
				store.deleteRecord(this.enumerator.nextRecordId());
			}
		}
		catch (RecordStoreException ex)
		{
			// error reading record store
		}
	}

	/**
	 * Deletes all records from both the RecordStore and the vector
	 */
	public void deleteAll()
	{
		this.scores.removeAllElements();
		this.clear();
	}

	/**
	 * Adds a high score
	 * @param name
	 * @param points
	 */
	public void add(String name, int points)
	{
		this.scores.addElement(new Score(name, points));
		this.sortAndTrim();
		this.save();
	}

	/**
	 * Returns the lowest score
	 * @return
	 */
	public int getLowestScore()
	{
		if (this.scores.size() > 0)
			return ((Score) this.scores.elementAt(this.scores.size() - 1)).points;
		else
			return 0;
	}

	/**
	 * Saves and closes the record store
	 */
	public void close()
	{
		try
		{
			// save before closing
			this.save();

			// close the record store
			this.store.closeRecordStore();
		}
		catch (RecordStoreException ex)
		{
			System.out.println("Error closing scores");
		}
	}

	/*
	 * Loads the record store content into the vector
	 */
	private void fillVector()
	{
        try
		{
            byte[] recordBytes;

            while(this.enumerator.hasNextElement())
			{
                recordBytes = this.enumerator.nextRecord();

				Score score = new Score(unpackName(recordBytes), unpackPoints(recordBytes));

				this.scores.addElement(score);
            }
        }
		catch (RecordStoreException ex)
		{
            System.out.println("Error al leer el RecordStore");
		}

	}

	/*
	 * Unpacks the name from the record bytes
	 * @param recordBytes
	 * @return
	 */
	private static String unpackName(byte[] recordBytes)
	{
		String s = new String(recordBytes, 0, NAME_SIZE);
        return s.substring(0, s.indexOf(0));
	}

	/*
	 * Unpacks the points from the record bytes
	 * @param recordBytes
	 * @return
	 */
	private static int unpackPoints(byte[] recordBytes)
	{
		int points = 0;
        byte[] pointsBytes = new byte[4];
		
        for(int i = 0; i < POINTS_SIZE; i++)
		{
            pointsBytes[i] = recordBytes[10+i];
        }
        try
		{
            DataInputStream stream = new DataInputStream(new ByteArrayInputStream(pointsBytes));
            points = stream.readInt();
            stream.close();
        }
		catch (IOException ex)
		{
            ex.printStackTrace();
        }
        return points;
	}

	/*
	 * Packs the name into bytes
	 * @param score
	 * @param record
	 */
	private static void packName(Score score, byte[] record)
	{
		int len = score.name.length();
		
		for (int nameByte = 0; nameByte < NAME_SIZE && nameByte < len; nameByte++)
		{
			record[nameByte] = (byte) score.name.charAt(nameByte);
		}
	}

	/*
	 * Packs the points into bytes
	 * @param score
	 * @param record
	 */
	private static void packPoints(Score score, byte[] record)
	{
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream(POINTS_SIZE);
		DataOutputStream dataStream = new DataOutputStream(byteStream);
		try
		{
			dataStream.writeInt(score.points);
			byteStream.close();
			dataStream.close();
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
		
		byte[] pointsBytes = byteStream.toByteArray();
		for (int pointByte = 0; pointByte < POINTS_SIZE; pointByte++)
		{
			record[NAME_SIZE + pointByte] = pointsBytes[pointByte];
		}
	}

	/*
	 * Sorts the vector in descending point order
	 * and trim it to the 5 highest elements.
	 */
	private void sortAndTrim()
	{
		// sort the vector
		int len = this.scores.size();
		Score a, b;
		for (int i = 0; i < len - 1; i++)
		{
			for (int j = 0; j < len - i - 1; j++)
			{
				a = (Score)this.scores.elementAt(j);
				b = (Score)this.scores.elementAt(j + 1);

				// if b has more points than a, move it up in the vector
				// by exchanging them
				if (b.points > a.points)
				{
					this.scores.setElementAt(b, j);
					this.scores.setElementAt(a, j + 1);
				}
			}
		}

		//trim the vector
		while (this.scores.size() > MAX_SCORES)
		{
			this.scores.removeElementAt(MAX_SCORES);
		}
	}

	/*
	 * Class that represents a score
	 * with name and points
	 */
	private class Score
	{
		public String name;
		public int points;

		public String toString()
		{
			return name + " " + points;
		}

		public Score(String name, int points)
		{
			this.name = name;
			this.points = points;
		}
	}	
}


