package com.ihere.voyage.util;

import org.apache.poi.hssf.util.PaneInformation;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public abstract class ExcelUtil {

	private ExcelUtil() {
	}

	/**
	 * 复制单元格
	 * 
	 * @param source 源单元格
	 * @param target 目标单元格
	 */
	public static void copyCell(Cell source, Cell target) {
		target.setCellStyle(source.getCellStyle());
		target.setCellType(source.getCellType());
		switch (source.getCellType()) {
			case Cell.CELL_TYPE_NUMERIC:
				target.setCellValue(source.getNumericCellValue());
				break;
			case Cell.CELL_TYPE_STRING:
				target.setCellValue(source.getStringCellValue());
				break;
			case Cell.CELL_TYPE_FORMULA:
				target.setCellFormula(source.getCellFormula());
				break;
			case Cell.CELL_TYPE_BLANK:
				break;
			case Cell.CELL_TYPE_BOOLEAN:
				target.setCellValue(source.getBooleanCellValue());
				break;
			case Cell.CELL_TYPE_ERROR:
				target.setCellErrorValue(source.getErrorCellValue());
				break;
			default:
				break;
		}
	}

	/**
	 * 判断指定区域内有没有合并单元格
	 * 
	 * @param sheet
	 * @param left
	 * @param top
	 * @param right
	 * @param bottom
	 * @return
	 */
	public static boolean isCoverMergedRegion(Sheet sheet, int left, int top, int right, int bottom) {
		int mergedRegions = sheet.getNumMergedRegions();
		for (int i = 0; i < mergedRegions; i++) {
			CellRangeAddress cra = sheet.getMergedRegion(i);
			int firstColumn = cra.getFirstColumn();
			int firstRow = cra.getFirstRow();
			int lastColumn = cra.getLastColumn();
			int lastRow = cra.getLastRow();
			// 列重叠 && 行重叠
			if (((firstColumn <= left && lastColumn >= left) || (firstColumn <= right && lastColumn >= right)) && ((firstRow <= top && lastRow >= top) || (firstRow <= bottom && lastRow >= bottom))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 插入列<br>
	 * 如果插入位置不是0，则使用插入位置前一列的样式、格式、列宽覆盖插入列；如果插入位置为0，则使用插入位置的样式、格式、列宽
	 * 
	 * @param sheet 操作的Sheet
	 * @param index 插入位置
	 * @param number 插入数量
	 */
	public static void insertColumn(Sheet sheet, int index, int number) {
		if (index < 0) {
			throw new IndexOutOfBoundsException(MessageFormat.format("插入位置(index:{0})不能小于0", index));
		}
		if (number <= 0) {
			return;
		}
		// 最后一行行数
		int lastRowNum = sheet.getLastRowNum();
		// 列数
		int columnNumber = 0;
		// 递归行移动单元格
		for (int i = 0; i <= lastRowNum; i++) {
			Row row = sheet.getRow(i);
			// 列数
			int lastCellNum = row.getLastCellNum();
			// 取最大列数
			columnNumber = Math.max(columnNumber, lastCellNum);
			// 递归移动列
			for (int j = lastCellNum; j >= index; j--) {
				// 源单元格
				Cell cell = row.getCell(j);
				// 目标单元格
				Cell newCell = row.createCell(j + number);
				if (cell != null) {
					// 复制
					copyCell(cell, newCell);
				}
				// 清除源单元格（插入的空白单元格）
				cell = row.createCell(j);
				// 如果不是在最前面插入，则复制插入位置前一列的样式和格式
				if (index > 0) {
					Cell leftCell = row.getCell(index - 1);
					if (leftCell != null) {
						cell.setCellStyle(leftCell.getCellStyle());
						cell.setCellType(leftCell.getCellType());
					}
				}
			}
		}
		// 修改移动后的列宽（保持移动以前的列宽）
		for (int i = columnNumber; i >= index; i--) {
			sheet.setColumnWidth(i + number, sheet.getColumnWidth(i));
		}
		// 修改插入的空白列的列宽（如果不是插入到最前面，则使用插入位置前一列的列宽；如果是最前面，则使用插入位置后一列的列宽）ps:如果插入位置是最前，不能使用sheet.getDefaultColumnWidth()
		int defaultColumnWidth = index == 0 ? sheet.getColumnWidth(number) : sheet.getColumnWidth(index - 1);
		for (int i = index; i < index + number; i++) {
			sheet.setColumnWidth(i, defaultColumnWidth);
		}
		// 移动合并单元格的位置
		int mergedRegions = sheet.getNumMergedRegions();
		for (int i = mergedRegions - 1; i >= 0; i--) {
			CellRangeAddress cra = sheet.getMergedRegion(i);
			if (cra.getLastColumn() >= index) {
				cra.setLastColumn(cra.getLastColumn() + number);
			}
			if (cra.getFirstColumn() >= index) {
				cra.setFirstColumn(cra.getFirstColumn() + number);
			}
			sheet.removeMergedRegion(i);
			sheet.addMergedRegion(cra);
		}
		// 移动冻结列的位置
		PaneInformation paneInfo = sheet.getPaneInformation();
		if (paneInfo != null && paneInfo.isFreezePane()) {
			int freezeRow = paneInfo.getHorizontalSplitTopRow();
			int freezeColumn = paneInfo.getVerticalSplitLeftColumn();
			if (index < freezeColumn) {
				sheet.createFreezePane(freezeColumn + number, freezeRow);
			}
		}
	}

	/**
	 * 复制列
	 * 
	 * @param sheet 操作的Sheet
	 * @param sourceIndex 源位置
	 * @param targetIndex 目标位置
	 */
	public static void copyColumn(Sheet sheet, int sourceIndex, int targetIndex) {
		copyColumn(sheet, sourceIndex, sourceIndex, targetIndex, 1);
	}

	/**
	 * 复制列
	 * 
	 * @param sheet 操作的Sheet
	 * @param begin 开始列（包含）
	 * @param end 结束列（包含）
	 * @param index 目标位置
	 */
	public static void copyColumn(Sheet sheet, int begin, int end, int index) {
		copyColumn(sheet, begin, end, index, 1);
	}

	/**
	 * 复制列
	 * 
	 * @param sheet 操作的Sheet
	 * @param begin 开始列（包含）
	 * @param end 结束列（包含）
	 * @param index 目标位置
	 * @param cycle 复制次数(<1时，不进行复制)
	 */
	public static void copyColumn(Sheet sheet, int begin, int end, int index, int cycle) {
		if (begin < 0) {
			throw new IndexOutOfBoundsException(MessageFormat.format("开始列(begin:{0})不能小于0", index));
		}
		if (end < begin) {
			throw new IndexOutOfBoundsException(MessageFormat.format("结束列(end:{0})不能小于开始列(begin:{1})", end, begin));
		}
		if (index < 0) {
			throw new IndexOutOfBoundsException(MessageFormat.format("插入位置(index:{0})不能小于0", index));
		}
		if (index > begin && index <= end) {
			throw new IndexOutOfBoundsException(MessageFormat.format("插入位置(index:{0})不能在选定区域内[{1}, {2})", index, begin, end));
		}
		if (cycle < 1) {
			return;
		}
		// sheet行数
		int rowNumber = sheet.getLastRowNum() + 1;
		// 待复制列单组列数
		int columnNumber = end - begin + 1;
		// 待复制列总列数
		int totalColumnNumber = columnNumber * cycle;
		// 先插入空白列
		insertColumn(sheet, index, totalColumnNumber);
		// 插入空白列后待复制单元格开始列
		int beginIndex = (index > begin) ? begin : (begin + totalColumnNumber);
		// 插入空白列后待复制单元格结束列
		int endIndex = beginIndex + (end - begin);
		// 记录待复制列单元格集合
		Cell[][] cellArea = new Cell[rowNumber][columnNumber];
		for (int i = 0; i < rowNumber; i++) {
			Row row = sheet.getRow(i);
			for (int j = 0; j < columnNumber; j++) {
				cellArea[i][j] = row.getCell(j + beginIndex);
			}
		}
		// 记录合并的单元格
		List<CellRangeAddress> craList = new ArrayList<>();
		int mergedRegions = sheet.getNumMergedRegions();
		for (int i = mergedRegions - 1; i >= 0; i--) {
			CellRangeAddress cra = sheet.getMergedRegion(i);
			if (cra.getFirstColumn() >= beginIndex && cra.getLastColumn() <= endIndex) {
				craList.add(cra);
			}
		}
		// 记录待复制列列宽
		int[] columnWidths = new int[columnNumber];
		for (int i = 0; i < columnNumber; i++) {
			columnWidths[i] = sheet.getColumnWidth(i + beginIndex);
		}
		// 循环复制列
		for (int times = 0; times < cycle; times++) {
			int copyIndex = index + columnNumber * times;
			// 复制一组
			for (int i = 0; i < rowNumber; i++) {
				Row row = sheet.getRow(i);
				for (int j = 0; j < columnNumber; j++) {
					Cell source = cellArea[i][j];
					Cell target = row.createCell(j + copyIndex);
					copyCell(source, target);
				}
			}
			// 修改列宽
			for (int i = 0; i < columnNumber; i++) {
				sheet.setColumnWidth(i + copyIndex, columnWidths[i]);
			}
			// 复制合并单元格
			int offset = copyIndex - beginIndex;
			for (CellRangeAddress cra : craList) {
				cra = cra.copy();
				cra.setFirstColumn(cra.getFirstColumn() + offset);
				cra.setLastColumn(cra.getLastColumn() + offset);
				// 如果目标位置在合并单元格的区域内（合并单元格有重复覆盖的单元格），则跳过
				if (!isCoverMergedRegion(sheet, cra.getFirstColumn(), cra.getFirstRow(), cra.getLastColumn(), cra.getLastRow())) {
					sheet.addMergedRegion(cra);
				}
			}
		}
	}

	/**
	 * 插入行<br>
	 * 如果插入位置不是0，则使用插入位置前一行的样式、格式、行高覆盖插入行；如果插入位置为0，则使用默认的样式、格式、行高
	 * 
	 * @param sheet 操作的Sheet
	 * @param index 插入位置
	 * @param number 插入数量
	 */
	public static void insertRow(Sheet sheet, int index, int number) {
		if (index < 0) {
			throw new IndexOutOfBoundsException(MessageFormat.format("插入位置(index:{0})不能小于0", index));
		}
		if (number <= 0) {
			return;
		}
		// 最后一行行数
		int lastRowNum = sheet.getLastRowNum();
		// 记录需要扩展的合并单元格
		List<CellRangeAddress> craList = new ArrayList<>();
		int mergedRegions = sheet.getNumMergedRegions();
		for (int i = mergedRegions - 1; i >= 0; i--) {
			CellRangeAddress cra = sheet.getMergedRegion(i);
			if (cra.getFirstRow() < index && cra.getLastRow() >= index) {
				craList.add(cra);
				sheet.removeMergedRegion(i);
			}
		}
		// 移动行
		if (index <= lastRowNum) {
			sheet.shiftRows(index, lastRowNum, number, true, true);
		}
		// 处理新插入行的样式、格式、行高
		if (index > 0) {
			Row tempRow = sheet.getRow(index - 1);
			if (tempRow != null) {
				short tempRowHeight = tempRow.getHeight();
				CellStyle tempRowStyle = tempRow.getRowStyle();
				int cellNumber = tempRow.getLastCellNum() + 1;
				for (int i = 0; i < number; i++) {
					Row row = sheet.createRow(i + index);
					row.setHeight(tempRowHeight);
					row.setRowStyle(tempRowStyle);
					for (int j = 0; j < cellNumber; j++) {
						Cell tempCell = tempRow.getCell(j);
						Cell cell = row.createCell(j);
						if (tempCell != null) {
							cell.setCellStyle(tempCell.getCellStyle());
							cell.setCellType(tempCell.getCellType());
						}
					}
				}
			}
		}
		// 扩展合并单元格
		for (CellRangeAddress cra : craList) {
			cra = cra.copy();
			cra.setLastRow(cra.getLastRow() + number);
			sheet.addMergedRegion(cra);
		}
		// 移动冻结行的位置
		PaneInformation paneInfo = sheet.getPaneInformation();
		if (paneInfo != null && paneInfo.isFreezePane()) {
			int freezeRow = paneInfo.getHorizontalSplitTopRow();
			int freezeColumn = paneInfo.getVerticalSplitLeftColumn();
			if (index < freezeRow) {
				sheet.createFreezePane(freezeColumn, freezeRow + number);
			}
		}
	}

	/**
	 * 复制行<font color="red">（未实现）</font>
	 * 
	 * @param sheet 操作的Sheet
	 * @param sourceIndex 源位置
	 * @param targetIndex 目标位置
	 */
	public static void copyRow(Sheet sheet, int sourceIndex, int targetIndex) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 复制行<font color="red">（未实现）</font>
	 * 
	 * @param sheet 操作的Sheet
	 * @param begin 开始行（包含）
	 * @param end 结束行（包含）
	 * @param index 目标位置
	 */
	public static void copyRow(Sheet sheet, int begin, int end, int index) {
		throw new UnsupportedOperationException();
	}

	/**
	 * 复制行<font color="red">（未实现）</font>
	 * 
	 * @param sheet 操作的Sheet
	 * @param begin 开始行（包含）
	 * @param end 结束行（包含）
	 * @param index 目标位置
	 * @param cycle 复制次数(<1时，不进行复制)
	 */
	public static void copyRow(Sheet sheet, int begin, int end, int index, int cycle) {
		throw new UnsupportedOperationException();
	}

}
