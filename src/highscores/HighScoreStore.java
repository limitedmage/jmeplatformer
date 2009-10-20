package highscores;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Vector;
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
	
	private static final int MAX_SCORES = 5;

	private static final int NAME_SIZE = 10;
	private static final int POINTS_SIZE = 4;
	private static final int TOTAL_SIZE = NAME_SIZE + POINTS_SIZE;

	public HighScoreStore()
	{
		scores = new Vector();

		try
		{
			store = RecordStore.openRecordStore("MetalVsPopHighScores", true);
		}
		catch (RecordStoreException ex)
		{
			// error opening record store
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

			// for now, just add to the record
			byte[] record = new byte[TOTAL_SIZE];
			
			HighScoreStore.packName(score, record);
			HighScoreStore.packPoints(score, record);

			try
			{
				store.addRecord(record, 0, record.length);
			}
			catch (RecordStoreException ex)
			{
				ex.printStackTrace();
			}
		}

	}

	public void load()
	{
		this.fillVector();		
	}

	public int size()
	{
		return this.scores.size();
	}

	public String elementAt(int index)
	{
		return this.scores.elementAt(index).toString();
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

	public void add(String name, int points)
	{
		this.scores.addElement(new Score(name, points));
		this.sortAndTrim();
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
			// error closing record store
		}
	}

	private void fillVector()
	{
        try
		{
            byte[] recordBytes;
            int recordSize = this.store.getNumRecords();


            for (int record = 1; record <= recordSize && record <= MAX_SCORES; record++)
			{
                recordBytes = this.store.getRecord(record);

				Score score = new Score(unpackName(recordBytes), unpackPoints(recordBytes));

				this.scores.addElement(score);
            }
        }
		catch (RecordStoreException ex)
		{
            System.out.println("Error al abrir el RecordStore");
		}

	}

	private static String unpackName(byte[] recordBytes)
	{
		String s = new String(recordBytes, 0, NAME_SIZE);
        return s.substring(0, s.indexOf(0));
	}

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

	private static void packName(Score score, byte[] record)
	{
		int len = score.name.length();
		
		for (int nameByte = 0; nameByte < NAME_SIZE && nameByte < len; nameByte++)
		{
			record[nameByte] = (byte) score.name.charAt(nameByte);
		}
	}

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


